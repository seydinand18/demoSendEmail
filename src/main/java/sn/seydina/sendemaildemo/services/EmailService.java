package sn.seydina.sendemaildemo.services;

public interface EmailService {

    void sendSimpleEmailMessage(String toEmail, String to, String Token);

    void sendMimeMessageWithAttachement(String toEmail, String to, String Token);

    void sendMimeMessageWithEmbeddedFiles(String toEmail, String to, String Token);

    void sendHtmlEmail(String toEmail, String to, String Token);

    void sendHtmlEmailWithEmbeddedFiles(String toEmail, String to, String Token);
}
