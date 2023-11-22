package Otwos.Donggae.domain.RecruitPost.controller;

import Otwos.Donggae.DAO.Recruit.RecruitPost;
import Otwos.Donggae.DAO.User.UserInterestField;
import Otwos.Donggae.DTO.RecruitPost.RecRecruitPostDTO;
import Otwos.Donggae.DTO.RecruitPost.RecruitPostDetailResponseDTO;
import Otwos.Donggae.DTO.RecruitPost.RecruitPostRequestDTO;
import Otwos.Donggae.DTO.RecruitPost.RecruitPostResponseDTO;
import Otwos.Donggae.DTO.RecruitPost.search.SearchRequest;
import Otwos.Donggae.DTO.RecruitPost.search.SearchResponse;
import Otwos.Donggae.DTO.team.teamDetail.TeamIdRequest;
import Otwos.Donggae.Jwt.Auth;
import Otwos.Donggae.domain.RecruitPost.Repository.RecruitPostRepository;
import Otwos.Donggae.domain.RecruitPost.service.RecRecruitPostService;
import Otwos.Donggae.domain.RecruitPost.service.RecruitPostService;
import Otwos.Donggae.domain.RecruitPost.service.SearchRecruitPostService;
import Otwos.Donggae.domain.member.repository.MemberRepository;
import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
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
    @Autowired
    private SearchRecruitPostService searchRecruitPostService;

    @GetMapping("/recruitPost/{recruitPostId}") // 게시글 조회
    public ResponseEntity<RecruitPostDetailResponseDTO> getRecruitPost(@PathVariable int recruitPostId){
        return ResponseEntity.ok(recruitPostService.getRecruitPost(recruitPostId));
    }

    @PostMapping("/recruitPost") // 글 작성
    public ResponseEntity<?> createRecruitPostAndTeam(@RequestBody RecruitPostRequestDTO recruitPostDTO,
                                                      @Auth int userId) {
        try {
            recruitPostService.createRecruitPostAndTeam(recruitPostDTO, userId);
            return ResponseEntity.ok("Successfully posted recruit Post");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/recruitPost/recommend") // 프로젝트 추천
    public ResponseEntity<?> recommendBoard(@Auth int userId) {
        try {
            List<RecRecruitPostDTO> recRecruitPostDTOList = recRecruitPostService.recommendRecruitPost(userId);
            if (recRecruitPostDTOList.isEmpty()) { // 사이트에 모집 글이 0개 일 때
                return ResponseEntity.notFound().build();
            }

            return ResponseEntity.ok().body(recRecruitPostDTOList);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/recruitPost/{recruitPostId}") // 게시글 삭제
    public ResponseEntity<?> deleteRecruitPost(@PathVariable int recruitPostId, @Auth int userId) {
        try {
            recruitPostService.deleteRecruitPost(recruitPostId, userId);
            return ResponseEntity.ok("Successfully deleated recruit Post");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/recruitPost/{recruitPostId}") // 게시글 수정
    public ResponseEntity<?> editRecruitPost(@PathVariable int recruitPostId,
                                             @RequestBody RecruitPostRequestDTO recruitPostRequestDTO,
                                             @Auth int userId) {
        try {
            recruitPostService.editRecruitPost(recruitPostId, recruitPostRequestDTO, userId);
            return ResponseEntity.ok("Successfully edited recruit Post");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/recruitPost/search") // 게시글 검색
    public ResponseEntity<?> searchRecruitPost(@RequestBody SearchRequest searchRequest) {
        try {
            SearchResponse searchResponse = searchRecruitPostService.searchRecruitPost(searchRequest);
            return ResponseEntity.ok().body(searchResponse);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/recruitPostPage") // 최신 게시글 4개 표시
    public ResponseEntity<?> searchRecruitPostPage() {
        try {
            SearchResponse searchResponse = searchRecruitPostService.showRecentFourPost();
            return ResponseEntity.ok().body(searchResponse);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/recruitPost/complete")
    public ResponseEntity<?> completeRecruitPost(@RequestBody TeamIdRequest teamIdRequest) {
        try {
            recruitPostService.completeRecruitPost(teamIdRequest);
            return ResponseEntity.ok("complete recruitPost");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
