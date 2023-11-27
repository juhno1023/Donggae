package Otwos.Donggae.domain.member.controller;

import Otwos.Donggae.DTO.member.myPage.MyPageRequestDTO;
import Otwos.Donggae.DTO.member.register.SignUpDTO;
import Otwos.Donggae.Jwt.Auth;
import Otwos.Donggae.domain.member.service.MemberService;
import Otwos.Donggae.domain.member.service.MyPageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class MyPageController {
    @Autowired
    private MemberService memberService;

    @Autowired
    private MyPageService myPageService;

    @GetMapping("/mypage")
    public ResponseEntity<?> showMyPage(@Auth int userId) {
        try {
            return ResponseEntity.ok(myPageService.showMyInfo(userId));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/mypage")
    public ResponseEntity<?> editMyPage(MyPageRequestDTO myPageRequestDTO, @Auth int userId) {
        try {
            myPageService.editMyInfo(myPageRequestDTO, userId);
            return ResponseEntity.ok("Successfully edited My Info");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
