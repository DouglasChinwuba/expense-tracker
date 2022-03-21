package com.expensetracker.notification.service;

import com.expensetracker.notification.model.Recipient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

@Service
public class EmailServiceImpl implements EmailService{

    private static final Logger logger = LoggerFactory.getLogger(EmailServiceImpl.class);

    @Autowired
    private RecipientServiceImpl recipientService;

    @Value("${expenseTracker.notification.email}")
    private String from;

    @Value("${expenseTracker.notification.password}")
    private String password;

    @Scheduled(cron = "${notify.cron}")
    @Override
    public void sendNotification(){
        String host = "smtp.gmail.com";
        String port = "587";

        Authenticator auth = new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(from, password);
            }
        };

        Properties properties = System.getProperties();
        properties.put("mail.smtp.host", host);
        properties.put("mail.smtp.port", port);
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");

        Session session = Session.getDefaultInstance(properties, auth);

        try {
            for(Recipient recipient : recipientService.findAllRecipients()){
                MimeMessage message = new MimeMessage(session);
                message.setFrom(new InternetAddress(from));
                message.addRecipient(Message.RecipientType.TO,
                        new InternetAddress(recipient.getUser().getEmail()));
                message.setSubject("Expense tracker account notification");

                String text = String.format("Hi %s,\n\n Your balance is $%.2f, your total income is $%.2f and total expense is $%.2f",
                                            recipient.getUser().getFirstname(), recipient.getBalance(), recipient.getIncome(), recipient.getExpense());
                message.setText(text);
                Transport.send(message);
            }
            logger.info("Successfully sent notifications");
        } catch (MessagingException e) {
            logger.error("Error sending notifications");
            e.printStackTrace();
        }
    }
}
