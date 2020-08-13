package com.sprint.summerproject.controllers;

import com.aliyuncs.exceptions.ClientException;
import com.sprint.summerproject.services.TelService;
import com.sprint.summerproject.services.EmailService;
import com.sprint.summerproject.utils.Response;
import org.springframework.mail.MailException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.mail.MessagingException;

@RestController
public class CodeController {

    private final EmailService emailService;

    public CodeController(EmailService emailService) {
        this.emailService = emailService;
    }

    @PostMapping("/code/tel")
    public Response createCodeToTel(@RequestParam String tel) {
        try {
            TelService.sendCode(tel);
            return new Response(200, "验证码发送成功！");
        } catch (ClientException e) {
            return new Response(0, "验证码发送失败！");
        }
    }

    @GetMapping("/code/tel")
    public Response checkCodeToTel(@RequestParam String tel, @RequestParam String code) {
        if (TelService.checkCode(tel, code)) {
            return new Response(200, "验证成功！");
        } else {
            return new Response(0, "验证码不正确，请检查后输入！");
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
