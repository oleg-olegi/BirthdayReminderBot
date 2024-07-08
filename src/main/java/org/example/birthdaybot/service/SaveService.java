package org.example.birthdaybot.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.example.birthdaybot.model.PersonData;
import org.example.birthdaybot.repository.DataRepository;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
@Log4j2
@RequiredArgsConstructor
public class SaveService {
    static String regex = "^(0?[1-9]|[12][0-9]|3[01])\\.(0?[1-9]|1[012])\\.(0?[1-2][0-9][0-9][0-9])\\s+([A-Za-zА-Яа-я]{3,}\\s+[A-Za-zА-Яа-я]{3,})$";
    private final static Pattern PATTERN = Pattern.compile(regex);
    private final DataRepository repository;


    public boolean addBirthday(String incomingMessage, Long chatId) {
        Matcher matcher = PATTERN.matcher(incomingMessage);
        log.info("Incoming message: {}", incomingMessage);
        if (matcher.find()) {
            String birthdayStr = matcher.group(1)+"."+matcher.group(2)+"."+matcher.group(3);
            log.info("Дата рождения: {}", birthdayStr);
            String name = matcher.group(4);
            log.info("Имя Фамилия: {}", name);
            try {
                SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
                Date date = dateFormat.parse(birthdayStr);
                PersonData data = new PersonData(chatId, name, date);
                repository.save(data);
                return true;
            } catch (ParseException e) {
                log.error("Ошибка при парсинге даты: {}", birthdayStr, e);
            }
        }
        log.info("Парсинг не удался");
        return false;
    }
}