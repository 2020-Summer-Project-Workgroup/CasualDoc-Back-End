package com.sprint.summerproject.controllers;

import com.aliyuncs.exceptions.ClientException;
import com.sprint.summerproject.services.TelService;
import com.sprint.summerproject.services.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;

@RestController
@CrossOrigin
public class CodeController {

    private final TelService telService;
    private final EmailService emailService;

    @Autowired
    public CodeController(TelService telService, EmailService emailService) {
        this.telService = telService;
        this.emailService = emailService;
    }

    @PostMapping("/code/tel")
    public String sendCodeToTel(@RequestParam String tel) {
        try {
            telService.sendCode(tel);
            return "Yes";
        } catch (ClientException e) {
            e.printStackTrace();
            return "No";
        }
    }

    @GetMapping("/code/tel")
    public String checkCodeOfTel(@RequestParam String tel, @RequestParam String code) {
        String _code = telService.retrieveCode(tel);
        if (_code != null) {
            return code.equals(_code) ? "Yes" : "No";
        } else {
            return "No";
        }
    }

    @PostMapping("/code/email")
    public String sendCodeViaEmail(@RequestParam String email) {
        try {
            emailService.sendNotification(email);
            return "Yes";
        } catch (MailException | MessagingException e) {
            return "No";
        }
    }

    @GetMapping("/code/email")
    public String checkCodeViaEmail(@RequestParam String email, @RequestParam String code) {
        if (emailService.checkCode(email, code)) {
            return "Yes";
        } else {
            return "No";
        }
    }

}
