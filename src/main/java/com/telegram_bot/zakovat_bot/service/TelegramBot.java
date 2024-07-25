/**
 * Author: Shaxzod Ro'ziqulov
 * User: Ruzikulov
 * DATE: 03.07.2024
 * TIME: 14:10
 */
package com.telegram_bot.zakovat_bot.service;

import com.telegram_bot.zakovat_bot.config.BotConfig;
import com.telegram_bot.zakovat_bot.entity.Flow;
import com.telegram_bot.zakovat_bot.entity.Question;
import com.telegram_bot.zakovat_bot.service.util.InlineKeyboard;
import com.telegram_bot.zakovat_bot.service.util.ReplyKeyBoard;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.commands.SetMyCommands;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.DeleteMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.commands.BotCommand;
import org.telegram.telegrambots.meta.api.objects.commands.scope.BotCommandScopeDefault;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class TelegramBot extends TelegramLongPollingBot {

    private final BotConfig botConfig;
    private final UserService userService;
    private final InlineKeyboard inlineKeyboard;
    private final ReplyKeyBoard replyKeyBoard;
    private final QuestionService questionService;
    private final FlowService flowService;

    public TelegramBot(BotConfig botConfig, UserService userService, InlineKeyboard inlineKeyboard, ReplyKeyBoard replyKeyBoard, QuestionService questionService, FlowService flowService) {
        super(botConfig.getToken());
        this.botConfig = botConfig;
        this.userService = userService;
        this.inlineKeyboard = inlineKeyboard;
        this.replyKeyBoard = replyKeyBoard;
        this.questionService = questionService;
        this.flowService = flowService;
        initializeBotCommands();
    }

    /**
     * Initialize bot commands.
     */
    private void initializeBotCommands() {
        List<BotCommand> commandList = new ArrayList<>();
        commandList.add(new BotCommand("/start", "get a welcome message"));
        try {
            this.execute(new SetMyCommands(commandList, new BotCommandScopeDefault(), null));
        } catch (TelegramApiException e) {
            log.error("Error setting bot commands: ", e);
        }
    }

    @Override
    public String getBotUsername() {
        return botConfig.getBotName();
    }

    @SneakyThrows
    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage() && update.getMessage().hasText()) {
            Long chatId = update.getMessage().getChatId();
            String messageText = update.getMessage().getText();
            try {
                handleUserState(update, messageText, chatId);
            } catch (TelegramApiException e) {
                log.error("Error handling user state: ", e);
                sendMessage(new SendMessage(chatId.toString(), "Xatolik yuz berdi!"));
            }
        } else if (update.hasCallbackQuery()) {
            String callbackData = update.getCallbackQuery().getData();
            Long chatId = update.getCallbackQuery().getMessage().getChatId();
            try {
                handleCallbackQuery(callbackData, chatId, update);
            } catch (TelegramApiException e) {
                log.error("Error handling callback query: ", e);
            }
        }
    }


    /**
     * Handle user state based on received message.
     */
    private void handleUserState(Update update, String messageText, Long chatId) throws TelegramApiException {
        switch (messageText) {
            case "/start":
                handleStartCommand(update, chatId);
                break;
            case "Boshlash":
                sendFirstQuestion(chatId);
                break;
            default:
                log.warn("Unknown command received: {}", messageText);
                sendMessage(new SendMessage(chatId.toString(), "Noma'lum buyruq: " + messageText));
        }
    }

    /**
     * Handle /start command.
     */
    private void handleStartCommand(Update update, Long chatId) throws TelegramApiException {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setText("Assalomu alaykum! Zakovat savollarini boshlaysizmi?");
        sendMessage.setChatId(chatId.toString());
        userService.registerUser(update.getMessage());
        sendMessage.setReplyMarkup(replyKeyBoard.startKeyboard());
        sendMessage(sendMessage);
    }

    /**
     * Send the first question to the user.
     */
    private void sendFirstQuestion(Long chatId) throws TelegramApiException {
        Question firstQuestion = questionService.getFirstQuestion();
        if (firstQuestion != null) {
            sendQuestion(chatId, firstQuestion);
        } else {
            sendMessage(new SendMessage(String.valueOf(chatId), "Savollar topilmadi."));
        }
    }

    /**
     * Handle callback queries.
     */
    private void handleCallbackQuery(String callbackData, Long chatId, Update update) throws TelegramApiException {
        String[] data = callbackData.split(":");
        Long questionId = Long.parseLong(data[0]);
        Long answerId = Long.parseLong(data[1]);

        Long userId = update.getCallbackQuery().getFrom().getId(); // Foydalanuvchi ID sini olish

        flowService.saveUserAnswer(userId, questionId, answerId); // Javobni saqlash

        deleteMessage(chatId, update); //habarlarni o'chirish

        Question nextQuestion = questionService.getNextQuestion(questionId);
        if (nextQuestion != null) {

            sendQuestion(chatId, nextQuestion);
        } else {
            sendMessage(new SendMessage(String.valueOf(chatId), "Barcha savollar tugadi."));
            sendAnswerStats(chatId, userId); // Foydalanuvchi javob statistikasini yuborish
            flowService.updateFlowStatusToOld(userId); //old statusga update qiladi
        }
    }

    /**
     * Delete message.
     */
    private void deleteMessage(Long chatId, Update update) throws TelegramApiException {
        Integer messageId = update.getCallbackQuery().getMessage().getMessageId();
        try {
            execute(new DeleteMessage(chatId.toString(), messageId));
        } catch (TelegramApiException e) {
            log.error("Xabarni o'chirishda xatolik: chatId={}, messageId={}", chatId, messageId, e);
            throw e;
        }
    }

    /**
     * Send a question to the user.
     */
    private void sendQuestion(Long chatId, Question question) throws TelegramApiException {
        SendMessage message = new SendMessage();
        message.setChatId(String.valueOf(chatId));
        message.setText(question.getQuestionText());
        InlineKeyboardMarkup inlineKeyboardMarkup = inlineKeyboard.answerKeyboardMarkup(question.getId());
        message.setReplyMarkup(inlineKeyboardMarkup);
        sendMessage(message);
    }

    /**
     * Send answer stats
     */
    private void sendAnswerStats(Long chatId, Long userId) throws TelegramApiException {
        long correctAnswers = flowService.countCorrectAnswers(userId);
        long incorrectAnswers = flowService.countIncorrectAnswers(userId);

        String statsMessage = String.format("To'g'ri javoblar: %d\nNoto'g'ri javoblar: %d", correctAnswers, incorrectAnswers);
        sendMessage(new SendMessage(String.valueOf(chatId), statsMessage));
    }

    /**
     * Execute send message action.
     */
    public void sendMessage(SendMessage sendMessage) throws TelegramApiException {
        execute(sendMessage);
    }
}
