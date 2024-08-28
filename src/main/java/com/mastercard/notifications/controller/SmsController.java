package com.mastercard.notifications.controller;

import com.mastercard.notifications.service.PinPointSMSService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/sms")
public class SmsController {

    private final PinPointSMSService pinPointSMSService;

    public SmsController(PinPointSMSService pinPointSMSService) {
        this.pinPointSMSService = pinPointSMSService;
    }

    @PostMapping("/send")
    public String sendSms(@RequestParam String phoneNumber, @RequestParam String message){
        return pinPointSMSService.sendSms(phoneNumber,message);
    }
}
