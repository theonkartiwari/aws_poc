package com.mastercard.notifications;

import com.mastercard.notifications.controller.SmsController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.env.CommandLinePropertySource;

@SpringBootApplication
public class NotificationsApplication implements CommandLineRunner {

	@Autowired
	private SmsController smsController;

	public static void main(String[] args) {
		SpringApplication.run(NotificationsApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		smsController.sendSms("+918692816832","HELLO ONKAR");
	}
}
