package Otwos.Donggae.domain.member.controller;

import Otwos.Donggae.DTO.member.email.GetEmailDTO;
import Otwos.Donggae.DTO.member.email.SendEmailCodeDTO;
import Otwos.Donggae.domain.member.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MailController {

    @Autowired
    private EmailService emailService;

    //email로 보안코드 전송
    @PostMapping("/sendemail")
    public ResponseEntity<?> sendSecurityCode(@RequestBody GetEmailDTO getEmailDTO) {
        try {
            SendEmailCodeDTO sendEmailCodeDTO = emailService.sendSecurityCodeToEmail(getEmailDTO.getEmail());
            return ResponseEntity.ok().body(sendEmailCodeDTO);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
