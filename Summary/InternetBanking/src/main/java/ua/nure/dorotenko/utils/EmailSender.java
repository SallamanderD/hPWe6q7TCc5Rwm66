package ua.nure.dorotenko.utils;

import ua.nure.dorotenko.exceptions.ApplicationException;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class EmailSender {
    private static EmailSender instance;
    private static final String USERNAME = "ad.internetbanking@gmail.com";
    private static final String PASSWORD = "Beeline1";
    private static final String CODE_SUBJECT = "IB: Registration Code";
    private static final String CODE_MESSAGE = "Your registration code: ";
    private static final String PIN_SUBJECT = "PIN for your card.";
    private static final String PIN_MESSAGE = "PIN for your card: ? is ?";
    private static Properties props;

    private EmailSender(){
        props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
    }

    public static EmailSender getInstance(){
        if(instance == null){
            instance = new EmailSender();
        }
        return instance;
    }

    public void sendCode(String toEmail, String code) throws ApplicationException {
        send(CODE_SUBJECT, CODE_MESSAGE + code, toEmail);
    }

    private void send(String subject, String text, String toEmail) throws ApplicationException{
        Session session = Session.getInstance(props, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(USERNAME, PASSWORD);
            }
        });
        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(USERNAME));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail));
            message.setSubject(subject);
            message.setText(text);
            Transport.send(message);
        } catch (MessagingException e) {
            throw new ApplicationException(Messages.ERR_SEND_EMAIL, e);
        }
    }

    public void sendPin(String toEmail, String PIN, String cardNumber) throws ApplicationException {
        StringBuilder sb = new StringBuilder(PIN_MESSAGE);
        sb.replace(sb.indexOf("?"), sb.indexOf("?") + 1, cardNumber);
        sb.replace(sb.indexOf("?"), sb.indexOf("?") + 1, PIN);
        send(PIN_SUBJECT, sb.toString(), toEmail);
    }
}
