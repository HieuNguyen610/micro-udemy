package hieu.statisticservice.service.impl;

import hieu.statisticservice.request.MessageRequest;
import hieu.statisticservice.service.EmailService;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

@Service
@RequiredArgsConstructor
public class EmailServiceImpl implements EmailService {

    private final TemplateEngine templateEngine;
    private final JavaMailSender mailSender;

    @Override
    public void sendEmail(MessageRequest request) throws MessagingException {
        MimeMessage message = mailSender.createMimeMessage();

        // Set Thymeleaf context
        Context context = new Context();
        context.setVariable("name", request.getTo());
        context.setVariable("content", request.getText());
        // Process template
        String htmlContent = templateEngine.process("welcome-email", context);

        MimeMessageHelper helper = new MimeMessageHelper(message, true);

        helper.setTo(request.getTo());
        helper.setSubject("Welcome to Our Service");
        helper.setText(htmlContent, true);
        helper.setFrom("harrynguyen610@gmail.com");

        mailSender.send(message);
    }
}
