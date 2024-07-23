/**
 * Author: Shaxzod Ro'ziqulov
 * User:Ruzikulov
 * DATE:07.07.2024
 * TIME:14:06
 */
package com.telegram_bot.zakovat_bot.service.util;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboard;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;

import java.util.ArrayList;
import java.util.List;
@Component
public class ReplyKeyBoard {
    public ReplyKeyboard startKeyboard() {
        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();

        replyKeyboardMarkup.setResizeKeyboard(true);
        replyKeyboardMarkup.setOneTimeKeyboard(true);

        List<KeyboardRow> rows = new ArrayList<>();
        KeyboardRow row = new KeyboardRow();

        KeyboardButton button = new KeyboardButton("Boshlash");

        row.add(button);
        rows.add(row);

        replyKeyboardMarkup.setKeyboard(rows);
        return replyKeyboardMarkup;
    }
}
