package org.techno.bot;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TechnoBot extends TelegramLongPollingBot {
    private static final String TELEGRAM_AUTH = "telegram_auth";

    public String getBotUsername() {
        return "TechnoBot";
    }

    public String getBotToken() {
        return System.getenv(TELEGRAM_AUTH);
    }

    public void onUpdateReceived(Update update) {
        if (update.hasMessage() && update.getMessage().hasText()) {
            // Set variables
            String userName = update.getMessage().getChat().getUserName();
            long userId = update.getMessage().getChat().getId();
            String message_text = update.getMessage().getText();

            // id from sent message (will use to send back the message)
            long chat_id = update.getMessage().getChatId();

            SendMessage message = new SendMessage(); // Create a message object
            message.setChatId(String.valueOf(chat_id));
            message.setText(message_text);
            log(userName, String.valueOf(userId), message_text, message_text);
            try {
                execute(message); // Sending our message object to user
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
        }
    }

    private void log(String username, String chatId, String textReceived, String botResponse) {
        System.out.println("----------------------------\n");
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd h:mm a");
        Date date = new Date();
        System.out.println(dateFormat.format(date));
        System.out.println("Message from " + username + ". (id = " + chatId + ") \n Text - " + textReceived);
        System.out.println("Bot answer: \n Text - " + botResponse);
    }
}
