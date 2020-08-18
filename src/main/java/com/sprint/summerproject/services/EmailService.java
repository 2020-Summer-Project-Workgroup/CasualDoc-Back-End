package com.sprint.summerproject.services;

import com.sprint.summerproject.storages.CodeStorage;
import com.sprint.summerproject.utils.CodeGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@Service
public class EmailService {

    private final CodeStorage codeStorage;
    private final CodeGenerator codeGenerator;
    private final JavaMailSender javaMailSender;
    private final TemplateEngine templateEngine;

    @Autowired
    public EmailService(JavaMailSender javaMailSender, TemplateEngine templateEngine,
                        CodeStorage codeStorage, CodeGenerator codeGenerator) {
        this.javaMailSender = javaMailSender;
        this.templateEngine = templateEngine;
        this.codeStorage = codeStorage;
        this.codeGenerator = codeGenerator;
    }

    public void sendNotification(String email) throws MailException, MessagingException {
        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);

        String code = codeGenerator.generateCode();

        Context context = new Context();
        context.setVariable("verificationCode", code);
        String mail = templateEngine.process("EmailVerificationCode.html", context);

        helper.setTo(email);
        helper.setFrom("casualdoc@163.com");
        helper.setSubject("欢迎使用 CasualDoc！");
        helper.setText(mail, true);

        javaMailSender.send(message);
        codeStorage.put(email, code);
    }

    public boolean checkCode(String email, String code) {
        String _code = codeStorage.get(email);
        if (_code != null) {
            return code.equals(_code);
        } else {
            return false;
        }
    }

}
