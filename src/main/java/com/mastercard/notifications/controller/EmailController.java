package com.mastercard.notifications.controller;

import com.mastercard.notifications.service.EmailService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/email")
public class EmailController {

    private final EmailService emailService;


    public EmailController(EmailService emailService) {
        this.emailService = emailService;
    }

    @GetMapping("/send")
    public ResponseEntity<String> sendEmail(@RequestParam String to, @RequestParam String subject, @RequestParam String body) {
        emailService.sendEmail(to, subject, body);
        return ResponseEntity.ok("Email sent successfully.");
    }

}
