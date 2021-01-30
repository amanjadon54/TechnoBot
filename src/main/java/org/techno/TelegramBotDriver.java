package org.techno;

import org.techno.bot.TechnoBot;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

public class TelegramBotDriver {
    public static void main(String[] args) {
        try {
            // Instantiate Telegram Bots API
            TelegramBotsApi botsApi = new TelegramBotsApi(DefaultBotSession.class);
            // Register our bot
            botsApi.registerBot(new TechnoBot());
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
}
