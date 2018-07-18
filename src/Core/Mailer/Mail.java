package Core.Mailer;

import java.util.Date;
import java.util.Properties;

import javax.mail.Authenticator;

import javax.mail.PasswordAuthentication;

import javax.mail.Session;
import javax.mail.Message;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.InternetAddress;
import javax.mail.Transport;

import javax.mail.internet.AddressException;
import javax.mail.NoSuchProviderException;
import javax.mail.MessagingException;

public class Mail {
    /**
     * @return Session
     */
    private static Session createSession() {
        final String username = "adrien.payen2@gmail.com";
        final String password = "33groupis";

        Properties		props	    = new Properties();
        props.setProperty("mail.from", "contact@breizhlink.fr");
        props.put("mail.smtp.host", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.auth", "true");
        props.setProperty("mail.smtp.ssl.trust", "*");

        return Session.getInstance(props,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });
    }

    /**
     * @param recipient Mail recipient
     * @param subject Mail subject
     * @param content Mail content
     */
    public void send(String recipient, String subject, String content) {
        try {
            //Creating a Message object to set the email content
            MimeMessage msg = new MimeMessage(createSession());

            msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(recipient, true));
            msg.setSentDate(new Date());
            msg.setSubject(subject);
            msg.setContent(content, "text/html; charset=utf-8");
            Transport.send(msg);
        } catch (MessagingException mex) {
            System.out.println("Unable to send an email" + mex);
        }
    }
}
