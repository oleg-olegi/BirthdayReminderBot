package org.example.birthdaybot.listner;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.UpdatesListener;
import com.pengrad.telegrambot.model.Message;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.SendMessage;
import jakarta.annotation.PostConstruct;
import lombok.extern.log4j.Log4j2;
import org.example.birthdaybot.service.SaveService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Log4j2
public class BotUpdateListener implements UpdatesListener {

    private final TelegramBot telegramBot;
    private final SaveService service;

    public BotUpdateListener(TelegramBot telegramBot, SaveService service) {
        this.telegramBot = telegramBot;
        this.service = service;
    }

    @PostConstruct
    public void init() {
        telegramBot.setUpdatesListener(this);
    }

    @Override
    public int process(List<Update> updates) {
        updates.forEach(update -> {
            log.info("Processing update: {}", update);
            // Process your updates here
            Message message = update.message();
            if (message != null && message.text() != null) {
//                log.info("Message is null");
//                return;
                Long chatId = message.chat().id();
                String text = message.text();

                log.info("Processing message: {}", text);

                if ("/start".equals(message.text())) {
                    String welcome = "Привет, " + message.chat().username() + ", введи дату " +
                            "рождения и имя в формате:\nДД.ММ.ГГГГ ИМЯ ФАМИЛИЯ";
                    telegramBot.execute(new SendMessage(chatId, welcome));
                    return;
                }
                if (service.addBirthday(message.text(), chatId)) {
                    String success = "\uD83D\uDE3B Напоминание добавлено успешно \uD83D\uDE3B";
                    telegramBot.execute(new SendMessage(chatId, success));
                } else {
                    String unsuccessfullyMessage = "Возможно, есть ошибки в формате сообщения \uD83E\uDD21" +
                            ". Формат сообщения - 29.02.1452 Имя Фамилия";
                    telegramBot.execute(new SendMessage(chatId, unsuccessfullyMessage));
                }
            } else {
                log.info("Message is null: {}", message);
            }
        });
        return UpdatesListener.CONFIRMED_UPDATES_ALL;
    }
}
