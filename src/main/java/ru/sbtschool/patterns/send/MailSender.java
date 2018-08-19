package ru.sbtschool.patterns.send;

import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import ru.sbtschool.patterns.dto.ReportDto;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

/**
 * Created by Home on 16.08.2018.
 */
public class MailSender {
    public static final String HOST = "mail.google.com";

    public void send( ReportDto reportDto ) throws MessagingException {
        // now when the report is built we need to send it to the recipients list
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        // we're going to use google mail to send this message
        mailSender.setHost( HOST );

        // construct the message
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper( message, true );
        helper.setTo( reportDto.getRecipients() );
        // setting message text, last parameter set 'true' if it is HTML format
        helper.setText( reportDto.getContent(), reportDto.isHtml() );
        helper.setSubject( reportDto.getSubject() );

        // send the message
        mailSender.send( message );
    }
}
