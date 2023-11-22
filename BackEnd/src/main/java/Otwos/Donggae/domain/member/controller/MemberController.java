package Otwos.Donggae.domain.member.controller;

import Otwos.Donggae.DTO.RecruitPost.RecRecruitPostDTO;
import Otwos.Donggae.DTO.member.register.SignUpDTO;
import Otwos.Donggae.DTO.member.register.ValidGithubIdRequest;
import Otwos.Donggae.DTO.team.RecMemberDTO;
import Otwos.Donggae.Jwt.Auth;
import Otwos.Donggae.domain.member.service.MemberService;
import Otwos.Donggae.domain.member.service.RecMemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class MemberController {

    @Autowired
    private MemberService memberService;

    @Autowired
    private RecMemberService recMemberService;

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

    @PostMapping("/member/recommend") // 멤버 추천
    public ResponseEntity<?> recommendMember(@Auth int userId){
        try{
            List<RecMemberDTO> recMemberDTOList = recMemberService.recommendMember(userId);
            if(recMemberDTOList.isEmpty()){
                return ResponseEntity.notFound().build();
            }

            return ResponseEntity.ok().body(recMemberDTOList);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

}
