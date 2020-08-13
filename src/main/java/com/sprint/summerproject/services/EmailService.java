package com.sprint.summerproject.services;

import com.sprint.summerproject.models.Code;
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
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class EmailService {

    private static Map<String, Code> codeMap = new ConcurrentHashMap<String, Code>();
    private final JavaMailSender javaMailSender;
    private final TemplateEngine templateEngine;

    @Autowired
    public EmailService(JavaMailSender javaMailSender, TemplateEngine templateEngine) {
        this.javaMailSender = javaMailSender;
        this.templateEngine = templateEngine;
    }

    public void sendNotification(String email) throws MailException, MessagingException {
        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);

        String code = CodeGenerator.generateCode();
        Code codeItem = new Code(code);

        Context context = new Context();
        context.setVariable("verificationCode", code);
        String mail = templateEngine.process("EmailVerificationCode.html", context);

        helper.setTo(email);
        helper.setFrom("casualdoc@163.com");
        helper.setSubject("欢迎使用 CasualDoc！");
        helper.setText(mail, true);

        javaMailSender.send(message);
        codeMap.put(email, codeItem);
    }

    public boolean checkCode(String email, String code) {
        Code codeItem;
        if ((codeItem = codeMap.get(email)) != null) {
            return code.equals(codeItem.getCode());
        } else {
            return false;
        }
    }

}
