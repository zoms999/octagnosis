package com.aptit.octagnosis.common;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class EmailSender {
    public static void sendEmail(String recipientEmail, String subject, String body) {
        // 보내는 사람 Gmail 계정 설정
        final String senderEmail = "hongsamcool3@gmail.com";
        final String password = "tlaaod12";

        // SMTP 서버 설정 (Gmail 기준)
        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");

        // SMTP 인증을 위한 Authenticator 생성
        Authenticator auth = new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(senderEmail, password);
            }
        };

        // 메일 세션 생성
        Session session = Session.getInstance(props, auth);

        try {
            // 메시지 객체 생성
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(senderEmail));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(recipientEmail));
            message.setSubject(subject);
            message.setText(body);

            // 이메일 전송
            Transport.send(message);

            System.out.println("이메일 전송 성공!");

        } catch (MessagingException e) {
            e.printStackTrace();
            System.out.println("이메일 전송 실패: " + e.getMessage());
        }
    }
}
