package com.expensetracker.notification.service;

import com.expensetracker.notification.model.Recipient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.mail.Message;
import javax.mail.MessagingException;
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

//    @Scheduled(cron = "${notify.cron}")
    @Scheduled(fixedRate = 2000L)
    @Override
    public void sendNotification(){
        final String from = "expensetracker@mail.com";

        String host = "127.0.0.1";
        String port = "587";

        Properties properties = System.getProperties();
        properties.put("mail.smtp.host", host);
        properties.put("mail.smtp.port", port);
        Session session = Session.getDefaultInstance(properties);

        try {
            for(Recipient recipient : recipientService.findAllRecipients()){
                MimeMessage message = new MimeMessage(session);
                message.setFrom(new InternetAddress(from));
                message.addRecipient(Message.RecipientType.TO,
                        new InternetAddress(recipient.getUser().getEmail()));
                message.setSubject("Expense tracker account notification");

                String text = String.format("Hi %s,\n Your balance is $%.2f, your total income is $%.2f and total expense is $%.2f",
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
