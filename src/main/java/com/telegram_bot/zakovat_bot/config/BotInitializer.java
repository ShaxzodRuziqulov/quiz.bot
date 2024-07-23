/**
 * Author: Shaxzod Ro'ziqulov
 * User:Ruzikulov
 * DATE:03.07.2024
 * TIME:14:09
 */
package com.telegram_bot.zakovat_bot.config;

import com.telegram_bot.zakovat_bot.service.TelegramBot;
import jakarta.annotation.PostConstruct;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

@Component
public class BotInitializer {
    public final TelegramBot telegramBot;

    public BotInitializer(TelegramBot telegramBot) {
        this.telegramBot = telegramBot;
    }

    @PostConstruct
    public void init() throws TelegramApiException {
        TelegramBotsApi telegramBotsApi = new TelegramBotsApi(DefaultBotSession.class);
        try {
            telegramBotsApi.registerBot(telegramBot);
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);
        }
    }
}
