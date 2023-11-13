package Otwos.Donggae.domain.RecruitPost.controller;

import Otwos.Donggae.DAO.Recruit.RecruitPost;
import Otwos.Donggae.DAO.User.User;
import Otwos.Donggae.DTO.RecruitPost.RecruitPostDTO;
import Otwos.Donggae.DTO.RecruitPost.RecruitPostRequestDTO;
import Otwos.Donggae.DTO.RecruitPost.RecruitPostResponseDTO;
import Otwos.Donggae.Jwt.Auth;
import Otwos.Donggae.domain.RecruitPost.Repository.RecruitPostRepository;
import Otwos.Donggae.domain.RecruitPost.service.RecruitPostService;
import Otwos.Donggae.domain.member.repository.MemberRepository;
import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class RecruitPostController {

    @Autowired
    private RecruitPostService recruitPostService;

//    @GetMapping("/recruitPost/{id}")  // 게시글 조회
//    public ResponseEntity<?> getRecruitPost(@PathVariable int recruitPostId){
//        RecruitPostResponseDTO recruitPostResponseDTO;
//        recruitPostResponseDTO = recruitPostService.getRecruitPost(recruitPostId);
//        return ResponseEntity.ok(recruitPostResponseDTO);
//    }

    @PostMapping("/recruitPost") // 글 작성
    public ResponseEntity<?> createRecruitPostAndTeam(@RequestBody RecruitPostRequestDTO recruitPostDTO, @Auth int userId){
        try {
            recruitPostService.createRecruitPostAndTeam(recruitPostDTO,userId);
            return ResponseEntity.ok("작성 완료");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/recruitPost/{recruitPostId}") // 게시글 삭제
    public ResponseEntity<?> deleteRecruitPost(@PathVariable int recruitPostId, @Auth int userId){
        try {
            recruitPostService.deleteRecruitPost(recruitPostId, userId);
            return ResponseEntity.ok("삭제 완료");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/recruitPost/{recruitPostId}") // 게시글 수정
    public ResponseEntity<?> editRecruitPost(@PathVariable int recruitPostId,@RequestBody RecruitPostRequestDTO recruitPostRequestDTO, @Auth int userId){
        try {
            recruitPostService.editRecruitPost(recruitPostId, recruitPostRequestDTO, userId);
            return ResponseEntity.ok("수정 완료");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
