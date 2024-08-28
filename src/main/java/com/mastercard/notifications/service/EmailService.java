package com.mastercard.notifications.service;

import org.springframework.stereotype.Service;
import software.amazon.awssdk.services.ses.SesClient;
import software.amazon.awssdk.services.ses.model.*;

@Service
public class EmailService {

    private final SesClient sesClient;

    public EmailService() {
        this.sesClient = SesClient.builder().build();
    }

    public void sendEmail(String toAddress, String subject, String body) {
        Destination destination = Destination.builder()
                .toAddresses(toAddress)
                .build();

        Message message = Message.builder()
                .subject(Content.builder().data(subject).build())
                .body(Body.builder().text(Content.builder().data(body).build()).build())
                .build();

        SendEmailRequest emailRequest = SendEmailRequest.builder()
                .destination(destination)
                .message(message)
                .source(toAddress)
                .build();

        sesClient.sendEmail(emailRequest);
    }
}
