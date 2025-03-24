package hieu.statisticservice.service;

import hieu.statisticservice.request.MessageRequest;
import jakarta.mail.MessagingException;

public interface EmailService {
    void sendEmail(MessageRequest message) throws MessagingException;
}
