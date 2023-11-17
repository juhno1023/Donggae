package Otwos.Donggae.domain.member.controller;

import Otwos.Donggae.DTO.member.register.SignUpDTO;
import Otwos.Donggae.DTO.member.register.ValidGithubIdRequest;
import Otwos.Donggae.domain.member.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MemberController {

    @Autowired
    private MemberService memberService;

    @PostMapping("/member/signup")
    public ResponseEntity<?> signUp(@RequestBody SignUpDTO signUpDTO) {
        try {
            memberService.register(signUpDTO);
            return ResponseEntity.ok("회원가입 완료");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/valid/githubid")
    public ResponseEntity<?> validateDuplicateGithubName(@RequestBody ValidGithubIdRequest request) {
        try {
            memberService.validGithubName(request);
            return ResponseEntity.ok("사용 가능한 githubName");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
