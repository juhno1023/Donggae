//package Otwos.Donggae.domain.RecruitPost.controller;
//
//import Otwos.Donggae.DAO.Recruit.RecruitPost;
//import Otwos.Donggae.DTO.RecruitPost.RecruitPostDTO;
//import Otwos.Donggae.DTO.RecruitPost.RecruitPostRequestDTO;
//import Otwos.Donggae.domain.RecruitPost.service.RecruitPostService;
//import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.ResponseEntity;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.*;
//
//@Controller
//public class RecruitPostController {
//
//    @Autowired
//    private RecruitPostService recruitPostService;
//
//    @PostMapping("/recruitPost") // 글 작성
//    public ResponseEntity<RecruitPostDTO> createBoard(@RequestBody RecruitPostRequestDTO recruitPostDTO){
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        if (authentication != null && authentication.getPrincipal() != "anonymousUser") {
//            return ResponseEntity.ok(recruitPostService.createBoard(recruitPostDTO,authentication));
//        }
//        return ResponseEntity.ok("모집 글 작성 실패");
//    }
//
//    @DeleteMapping("/recruitPost/{recruitPostId}") // 게시글 삭제
//    public ResponseEntity<String> deleteBoard(@PathVariable int recruitPostId){
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        if (authentication != null && authentication.getPrincipal() != "anonymousUser") {
//            return ResponseEntity.ok(recruitPostService.deleteBoard(recruitPostId,authentication));
//        }
//        return ResponseEntity.ok("fail");
//    }
//
//    @PutMapping("/recruitPost/{recruitPostId}") // 게시글 수정
//    public ResponseEntity<RecruitPostDTO> editReply(@PathVariable int recruitPostId,@RequestBody RecruitPostRequestDTO recruitPostRequestDTO){
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        if(authentication != null && authentication.getPrincipal() != "anonymousUser"){
//            return ResponseEntity.ok(recruitPostService.editRecruitPost(recruitPostId, recruitPostRequestDTO));
//        }
//        return ResponseEntity.notFound().build();
//    }
//}
