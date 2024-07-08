package org.example.birthdaybot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class BirthdayBotApplication {

	public static void main(String[] args) {
		SpringApplication.run(BirthdayBotApplication.class, args);
	}

}
