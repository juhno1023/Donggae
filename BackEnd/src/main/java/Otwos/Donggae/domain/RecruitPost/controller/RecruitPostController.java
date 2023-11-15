package Otwos.Donggae.domain.RecruitPost.controller;

import Otwos.Donggae.DAO.User.UserInterestField;
import Otwos.Donggae.DTO.RecruitPost.RecRecruitPostDTO;
import Otwos.Donggae.DTO.RecruitPost.RecruitPostRequestDTO;
import Otwos.Donggae.Jwt.Auth;
import Otwos.Donggae.domain.RecruitPost.service.RecRecruitPostService;
import Otwos.Donggae.domain.RecruitPost.service.RecruitPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class RecruitPostController {

    @Autowired
    private RecruitPostService recruitPostService;

    @Autowired
    private RecRecruitPostService recRecruitPostService;

    @PostMapping("/recruitPost") // 글 작성
    public ResponseEntity<?>  createBoard(@RequestBody RecruitPostRequestDTO recruitPostDTO, @Auth int userId){
        try {
            recruitPostService.createRecruitPostAndTeam(recruitPostDTO,userId);
            return ResponseEntity.ok("작성 완료");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/recruitPost/recommend") // 프로젝트 추천
    public ResponseEntity<?> recommendBoard(int userId){
        try{
            List<UserInterestField> recRecruitPostDTOList = recRecruitPostService.recommendRecruitPost(userId);
            return ResponseEntity.ok().body(recRecruitPostDTOList);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }



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
}
