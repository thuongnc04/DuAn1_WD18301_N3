package com.example.duan1_wd18301_n3.Email;

import android.os.AsyncTask;

import java.net.Authenticator;
import java.net.PasswordAuthentication;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;


public class SendEmail extends AsyncTask {

    private String email;
    private String code;

    public SendEmail(String email, String code) {
        this.email = email;
        this.code = code;
    }
    @Override
    protected Object doInBackground(Object[] objects) {

//        // Thông tin tài khoản email người gửi
//        String senderEmail = "";
//        String senderPassword = "";
//
//
//        // Thông tin tài khoản email người nhận
//        String receiverEmail = email;
//
//        // Cấu hình thông tin SMTP server
//        Properties props = new Properties();
//        props.put("mail.smtp.host", "smtp.gmail.com");
//        props.put("mail.smtp.socketFactory.port", "465");
//        props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
//        props.put("mail.smtp.auth", "true");
//        props.put("mail.smtp.port", "465");
//
//         //Tạo đối tượng Session để xác thực tài khoản và gửi email
//        Session session = Session.getDefaultInstance(props, new Authenticator() {
//            protected PasswordAuthentication getPasswordAuthentication() {
//                return new PasswordAuthentication(senderEmail, senderPassword);
//            }
//        });
//
//        try {
//            // Tạo đối tượng MimeMessage để định dạng email
//            MimeMessage message = new MimeMessage(session);
//            message.setFrom(new InternetAddress(senderEmail));
//            message.addRecipient(Message.RecipientType.TO, new InternetAddress(receiverEmail));
//            message.setSubject("Mã xác nhận");
//            message.setText("Đây là mã xác nhận của bạn: " + code);
//
//            // Gửi email
//            Transport.send(message);
//        } catch (MessagingException e) {
//            throw new RuntimeException(e);
//        }
        return null;
   }
}
