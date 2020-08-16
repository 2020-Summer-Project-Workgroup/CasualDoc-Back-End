package com.sprint.summerproject.controllers;

import com.aliyuncs.exceptions.ClientException;
import com.sprint.summerproject.services.TelService;
import com.sprint.summerproject.services.EmailService;
import com.sprint.summerproject.utils.Response;
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
        if (code != null) {
            return code.equals(_code) ? "Yes" : "No";
        } else {
            return "No";
        }
    }

    @PostMapping("/code/email")
    public Response sendCodeViaEmail(@RequestParam String email) {
        try {
            emailService.sendNotification(email);
            return new Response(200, "Message sent successfully");
        } catch (MailException | MessagingException e) {
            return new Response(0, "Something went wrong");
        }
    }

    @GetMapping("/code/email")
    public Response checkCodeViaEmail(@RequestParam String email, @RequestParam String code) {
        if (emailService.checkCode(email, code)) {
            return new Response(200, "验证成功");
        } else {
            return new Response(0, "验证码不正确，请检查后输入");
        }
    }

}
