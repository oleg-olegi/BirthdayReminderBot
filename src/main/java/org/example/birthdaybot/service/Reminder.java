package org.example.birthdaybot.service;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.request.SendMessage;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.example.birthdaybot.model.PersonData;
import org.example.birthdaybot.repository.DataRepository;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

@Component
@AllArgsConstructor
@Log4j2
public class Reminder {

    private DataRepository dataRepository;
    private TelegramBot telegramBot;

    //    @Scheduled(cron = "0 0 19 * * ?")

    @Scheduled(cron = "0/10 * * * * *")
    public void doReminder() {
        log.info("В планировщике");
        LocalDate tomorrow = LocalDate.now().plusDays(1);
        log.info("Завтра: {}", tomorrow.toString());
        List<PersonData> personData = dataRepository.findAll();
        personData.forEach(data -> {
            LocalDate birthday = convertToLocalDate(data.getBirthday());
            birthday = birthday.withYear(LocalDate.now().getYear());
            if (birthday.equals(tomorrow)) {
                sendMessagesToAllChats(personData, data);
//                String remindMsg = "Завтра день рождения у " + data.getFullName() + "\uD83D\uDCA5 - не забудь поздравить";
//                personData.forEach(chatId -> telegramBot.execute(new SendMessage(chatId.getChatId(), remindMsg)));
            }
        });
    }

    private void sendMessagesToAllChats(List<PersonData> personData, PersonData data) {
        String remindMsg = "Завтра день рождения у " + data.getFullName() + "\uD83D\uDCA5 - не забудь поздравить";
        personData.forEach(chatId -> telegramBot.execute(new SendMessage(chatId.getChatId(), remindMsg)));
    }

    private LocalDate convertToLocalDate(Date dateToConvert) {
        log.info("В конвертере даты: {}", dateToConvert.toString());

        return dateToConvert.toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDate();
    }
}
