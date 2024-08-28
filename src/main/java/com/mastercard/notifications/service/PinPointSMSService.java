package com.mastercard.notifications.service;

import jakarta.annotation.PostConstruct;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.pinpoint.PinpointClient;
import software.amazon.awssdk.services.pinpoint.model.DirectMessageConfiguration;
import software.amazon.awssdk.services.pinpoint.model.MessageRequest;
import software.amazon.awssdk.services.pinpoint.model.SendMessagesRequest;
import software.amazon.awssdk.services.pinpoint.model.SendMessagesResponse;
import software.amazon.awssdk.services.pinpoint.model.SMSMessage;
import software.amazon.awssdk.services.pinpoint.model.AddressConfiguration;
import software.amazon.awssdk.services.pinpoint.model.PinpointException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class PinPointSMSService {

    @Value("${aws.region}")
    private String awsRegion;

    @Value("${aws.pinpoint.project-id}")
    private String pinpointProjectId;

    private PinpointClient pinpointClient;

    @PostConstruct
    public void init() {
        pinpointClient = PinpointClient.builder()
                .region(Region.of(awsRegion))
                .build();
    }

    public String sendSms(String phoneNumber, String message) {
        try {
            // Create the SMS message
            SMSMessage smsMessage = SMSMessage.builder()
                    .body(message)
                    .messageType("TRANSACTIONAL")
                    .build();

            // Create the DirectMessageConfiguration with the SMS message
            DirectMessageConfiguration directMessageConfiguration = DirectMessageConfiguration.builder()
                    .smsMessage(smsMessage)
                    .build();

            // Map the phone number to the AddressConfiguration
            Map<String, AddressConfiguration> addressMap = new HashMap<>();
            addressMap.put(phoneNumber, AddressConfiguration.builder()
                    .channelType("SMS")
                    .build());

            // Create the MessageRequest
            MessageRequest messageRequest = MessageRequest.builder()
                    .addresses(addressMap)
                    .messageConfiguration(directMessageConfiguration)
                    .build();

            // Create the SendMessagesRequest
            SendMessagesRequest sendMessagesRequest = SendMessagesRequest.builder()
                    .applicationId(pinpointProjectId)
                    .messageRequest(messageRequest)
                    .build();

            // Send the message
            SendMessagesResponse response = pinpointClient.sendMessages(sendMessagesRequest);
            return response.messageResponse().result().toString();
        } catch (PinpointException e) {
            e.printStackTrace();
            return "Failed to send SMS: " + e.getMessage();
        }
    }
}
