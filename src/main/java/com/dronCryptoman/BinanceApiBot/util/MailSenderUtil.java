package com.dronCryptoman.BinanceApiBot.util;

import lombok.RequiredArgsConstructor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.IOException;

@Component
@RequiredArgsConstructor
public class MailSenderUtil {
    private final JavaMailSender javaMailSender;

    public void sendMessage(String email, String symbol, Double price) throws IOException, MessagingException {
        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true, "utf-8");
        message.setContent("Current price for "+symbol+" is "+price.toString(), "text/html");
        helper.setTo(email);
        helper.setSubject("Binance Alert");
        javaMailSender.send(message);



    }
}
