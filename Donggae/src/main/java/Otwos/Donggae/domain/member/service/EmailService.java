package Otwos.Donggae.domain.member.service;

import Otwos.Donggae.DTO.member.email.SendEmailCodeDTO;

public interface EmailService {

    public void sendEmail(String to, String text);

    public SendEmailCodeDTO sendSecurityCodeToEmail(String id);
}
