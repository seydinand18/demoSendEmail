package sn.seydina.sendemaildemo.services.impl;

import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.thymeleaf.ITemplateEngine;
import org.thymeleaf.context.Context;
import sn.seydina.sendemaildemo.services.EmailService;
import sn.seydina.sendemaildemo.utils.EmailUtils;
import java.io.File;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class EmailServiceImpl implements EmailService {

    @Value("${spring.mail.verify.host}")
    private String host;

    @Value("${spring.mail.username}")
    private String fromMail;

    private final JavaMailSender javaMailSender;
    private final ITemplateEngine templateEngine;

    @Override
    @Async
    public void sendSimpleEmailMessage(String name, String to, String Token) {
        try {
            SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
            simpleMailMessage.setSubject("Confirmation de votre inscription");
            simpleMailMessage.setFrom(fromMail);
            simpleMailMessage.setTo(to);
            simpleMailMessage.setText(EmailUtils.getEmailMessage(name, host, Token));
            javaMailSender.send(simpleMailMessage);
        }catch (Exception e) {
            System.out.println(e.getMessage());
            throw new RuntimeException("Erreur lors de l'envoi de l'email");
        }
    }

    @Override
    @Async
    public void sendMimeMessageWithAttachement(String name, String to, String Token) {
        try {

            MimeMessage message = getMimeMessage();

            MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");
            helper.setPriority(1);
            helper.setSubject("Confirmation de votre inscription");
            helper.setFrom(fromMail);
            helper.setTo(to);
            helper.setText(EmailUtils.getEmailMessage(name, host, Token));

            //Add attachment
            FileSystemResource logo = new FileSystemResource(new File(System.getProperty("user.home")+ "/Pictures/logo_dff.png"));
            FileSystemResource seydina = new FileSystemResource(new File(System.getProperty("user.home")+ "/Pictures/profil.JPG"));

            helper.addAttachment(logo.getFilename(), logo);
            helper.addAttachment(seydina.getFilename(), seydina);

            javaMailSender.send(message);

        }catch (Exception e) {
            System.out.println(e.getMessage());
            throw new RuntimeException("Erreur lors de l'envoi de l'email");
        }
    }

    @Override
    @Async
    public void sendMimeMessageWithEmbeddedFiles(String name, String to, String Token) {
        try {

            MimeMessage message = getMimeMessage();

            MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");
            helper.setPriority(1);
            helper.setSubject("Confirmation de votre inscription");
            helper.setFrom(fromMail);
            helper.setTo(to);
            helper.setText(EmailUtils.getEmailMessage(name, host, Token));

            //Add attachment
            FileSystemResource logo = new FileSystemResource(new File(System.getProperty("user.home")+ "/Pictures/logo_dff.png"));
            FileSystemResource seydina = new FileSystemResource(new File(System.getProperty("user.home")+ "/Pictures/profil.JPG"));

            helper.addInline(getContentId(logo.getFilename()), logo);
            helper.addInline(getContentId(seydina.getFilename()), seydina);

            javaMailSender.send(message);

        }catch (Exception e) {
            System.out.println(e.getMessage());
            throw new RuntimeException("Erreur lors de l'envoi de l'email");
        }
    }

    @Override
    @Async
    public void sendHtmlEmail(String name, String to, String Token) {
        try {

            // Create the HTML body using Thymeleaf
            Context context = new Context();
            /*context.setVariable("name", name);
            context.setVariable("url", EmailUtils.getVerificationUrl(host, Token));*/
            context.setVariables(Map.of(
                    "name", name,
                    "url", EmailUtils.getVerificationUrl(host, Token)
            ));

            String htmlBody = templateEngine.process("email/email-template", context);

            MimeMessage message = getMimeMessage();

            MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");
            helper.setPriority(1);
            helper.setSubject("Confirmation de votre inscription");
            helper.setFrom(fromMail);
            helper.setTo(to);
            helper.setText(htmlBody, true);

            javaMailSender.send(message);

        }catch (Exception e) {
            System.out.println(e.getMessage());
            throw new RuntimeException("Erreur lors de l'envoi de l'email");
        }
    }

    @Override
    @Async
    public void sendHtmlEmailWithEmbeddedFiles(String name, String to, String Token) {
        try {

            // Create the HTML body using Thymeleaf
            Context context = new Context();

            context.setVariables(Map.of(
                    "name", name,
                    "url", EmailUtils.getVerificationUrl(host, Token)
            ));

            String htmlBody = templateEngine.process("email/email-template", context);

            MimeMessage message = getMimeMessage();

            MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");
            helper.setPriority(1);
            helper.setSubject("Confirmation de votre inscription");
            helper.setFrom(fromMail);
            helper.setTo(to);
            helper.setText(htmlBody, true);

            //Add attachment
            FileSystemResource logo = new FileSystemResource(new File(System.getProperty("user.home")+ "/Pictures/logo_dff.png"));
            FileSystemResource seydina = new FileSystemResource(new File(System.getProperty("user.home")+ "/Pictures/profil.JPG"));

            helper.addAttachment(logo.getFilename(), logo);
            helper.addAttachment(seydina.getFilename(), seydina);

            javaMailSender.send(message);

        }catch (Exception e) {
            System.out.println(e.getMessage());
            throw new RuntimeException("Erreur lors de l'envoi de l'email");
        }
    }

    private MimeMessage getMimeMessage() {
        return javaMailSender.createMimeMessage();
    }

    private String getContentId(String filename) {
        return "<" + filename + ">";
    }

}
