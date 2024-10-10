package org.example.birthdaybot.service;

import org.junit.jupiter.api.Test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SaveService {
    static String regex = "^(0?[1-9]|[12][0-9]|3[01])\\.(0?[1-9]|1[012])\\.(0?[1-2][0-9][0-9][0-9])\\s+([A-Za-zА-Яа-я]{3,}\\s+[A-Za-zА-Яа-я]{3,})$";
    private final static Pattern PATTERN = Pattern.compile(regex);

    @Test
    void test() {
        String incoming = "04.05.1992 Игорь Коотья";

        Matcher matcher = PATTERN.matcher(incoming);
        if (matcher.find()) {
            String birthdayStr = matcher.group(1) + "." + matcher.group(2) + "." + matcher.group(3);
            String name = matcher.group(4);
            assert birthdayStr.equals("04.05.1992");
            assert name.equals("Игорь Коотья");
        }
    }
}
