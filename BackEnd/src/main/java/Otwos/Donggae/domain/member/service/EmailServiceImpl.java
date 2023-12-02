package Otwos.Donggae.domain.member.service;

import Otwos.Donggae.DTO.member.email.SendEmailCodeDTO;
import java.security.SecureRandom;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailServiceImpl implements EmailService{

    private final JavaMailSender javaMailSender;

    @Autowired
    public EmailServiceImpl(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    @Override
    public void sendEmail(String to, String text) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to+"@dgu.ac.kr");
        message.setSubject("이메일 인증 코드: ");
        message.setText(text);
        javaMailSender.send(message);
    }

    //보안 코드 생성 메서드
    private String generateSecurityCode() {
        SecureRandom secureRandom = new SecureRandom();
        int code = secureRandom.nextInt(10000);
        return String.format("%04d", code);
    }

    //보안 코드를 이메일로 전송하는 메서드
    @Override
    public SendEmailCodeDTO sendSecurityCodeToEmail(String id){

        String securityCode = generateSecurityCode(); //보안코드 생성

        String text = "보안 코드: " + securityCode;
        sendEmail(id, text);

        return new SendEmailCodeDTO(securityCode);
    }
}
