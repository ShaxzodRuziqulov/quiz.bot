/**
 * Author: Shaxzod Ro'ziqulov
 * User:Ruzikulov
 * DATE:06.07.2024
 * TIME:19:09
 */
package com.telegram_bot.zakovat_bot.service.util;

import com.telegram_bot.zakovat_bot.entity.Answer;
import com.telegram_bot.zakovat_bot.repository.AnswerRepository;
import com.telegram_bot.zakovat_bot.service.AnswerService;
import com.telegram_bot.zakovat_bot.service.TelegramBot;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;


import java.util.*;


@Component
public class InlineKeyboard {

    public final AnswerService answerService;
    public final AnswerRepository answerRepository;

    public InlineKeyboard(AnswerService answerService, AnswerRepository answerRepository) {
        this.answerService = answerService;
        this.answerRepository = answerRepository;
    }


    public InlineKeyboardMarkup answerKeyboardMarkup(Long questionId) {
        List<Answer> answers = answerService.getAnswersByQuestionId(questionId);

        List<List<InlineKeyboardButton>> buttons = new ArrayList<>();

        for (Answer answer : answers) {
            List<InlineKeyboardButton> row = new ArrayList<>();
            InlineKeyboardButton button = new InlineKeyboardButton();

            button.setText(answer.getAnswerOption() + ": " + answer.getAnswerText());
            button.setCallbackData(questionId + ":" + answer.getId());
            row.add(button);
            buttons.add(row);
        }

        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
        inlineKeyboardMarkup.setKeyboard(buttons);

        return inlineKeyboardMarkup;
    }
    public InlineKeyboardMarkup sendButton(){
        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();

        List<List<InlineKeyboardButton>> row = new ArrayList<>();
        List<InlineKeyboardButton> buttonList = new ArrayList<>();

        InlineKeyboardButton button = new InlineKeyboardButton("Javoblarni ko'rish");
        button.setCallbackData("show_answers");

        buttonList.add(button);
        row.add(buttonList);

        inlineKeyboardMarkup.setKeyboard(row);
        return inlineKeyboardMarkup;
    }




}
