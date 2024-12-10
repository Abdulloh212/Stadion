package uz.pdp.stadium.Data;

import com.sun.activation.registries.MailcapParseException;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.sql.DataSource;
import java.io.*;
import java.util.Properties;

public class SendMessage {

    public static void sendMessage(String userGmailName, int part1) throws MessagingException {
        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "465");
        props.put("mail.smtp.ssl.enable", "true");
        props.put("mail.smtp.auth", "true");

        String username = "masturabonu.1985@gmail.com";
        String password = "obhymsqwxxhovtgv";

        Session session = Session.getInstance(props, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });

        Message message = new MimeMessage(session);
        message.setSubject("PASSWORD");
        message.setFrom(new InternetAddress(username));
        message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(userGmailName));

        Multipart multipart = new MimeMultipart();
        BodyPart contentMessage = new MimeBodyPart();
        contentMessage.setText(String.valueOf(part1));
        multipart.addBodyPart(contentMessage);

        message.setContent(multipart);

        Transport.send(message);
        System.out.println("Email successfully sent!");
    }




}

