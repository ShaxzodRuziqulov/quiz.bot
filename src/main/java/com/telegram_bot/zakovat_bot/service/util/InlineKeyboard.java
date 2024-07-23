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
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;


import java.util.*;


@Component
public class InlineKeyboard {

    public final AnswerService answerService;
    public final AnswerRepository answerRepository;

    public InlineKeyboard(AnswerService answerService, AnswerRepository answerRepository) {
        this.answerService = answerService;
        this.answerRepository = answerRepository;
    }



    public InlineKeyboardMarkup answerKeyboardMarkup() {
        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> rowsInline = new ArrayList<>();
        List<InlineKeyboardButton> rowInline = new ArrayList<>();

        InlineKeyboardButton button1 = new InlineKeyboardButton();
        button1.setText("Answer 1"); // Set text for button1

        InlineKeyboardButton button2 = new InlineKeyboardButton();
        button2.setText("Answer 2"); // Set text for button2

        InlineKeyboardButton button3 = new InlineKeyboardButton();
        button3.setText("Answer 3"); // Set text for button3

        InlineKeyboardButton button4 = new InlineKeyboardButton();
        button4.setText("Answer 4"); // Set text for button4

        button1.setCallbackData("answer1_command"); // Set callback data for button1
        button2.setCallbackData("answer2_command"); // Set callback data for button2
        button3.setCallbackData("answer3_command"); // Set callback data for button3
        button4.setCallbackData("answer4_command"); // Set callback data for button4

        rowInline.add(button1);
        rowInline.add(button2);
        rowsInline.add(rowInline);

        rowInline = new ArrayList<>(); // Clear the row for next set of buttons

        rowInline.add(button3);
        rowInline.add(button4);
        rowsInline.add(rowInline);

        inlineKeyboardMarkup.setKeyboard(rowsInline);

        return inlineKeyboardMarkup;
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


}
