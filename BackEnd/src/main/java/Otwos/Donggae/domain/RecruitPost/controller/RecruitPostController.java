package Otwos.Donggae.domain.RecruitPost.controller;

import Otwos.Donggae.DTO.RecruitPost.*;
import Otwos.Donggae.DTO.RecruitPost.search.SearchRequest;
import Otwos.Donggae.DTO.RecruitPost.search.SearchResponse;
import Otwos.Donggae.DTO.team.showMyTeam.MyRecruitPostNameList;
import Otwos.Donggae.DTO.team.showMyTeam.TeamByMember;
import Otwos.Donggae.DTO.team.teamDetail.TeamIdRequest;
import Otwos.Donggae.Jwt.Auth;
import Otwos.Donggae.domain.RecruitPost.service.RecRecruitPostService;
import Otwos.Donggae.domain.RecruitPost.service.RecruitPostService;
import Otwos.Donggae.domain.RecruitPost.service.SearchRecruitPostService;
import Otwos.Donggae.domain.RecruitPost.service.SuggestRecruitPostService;
import Otwos.Donggae.domain.team.service.TeamService;
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
    @Autowired
    private SuggestRecruitPostService suggestRecruitPostService;
    @Autowired
    private TeamService teamService;

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

    @GetMapping("/recruitPost/suggest")
    public ResponseEntity<?> showSuggestRecruitPosts(@Auth int userId) {
        try {
            List<TeamByMember> suggests = suggestRecruitPostService.showSuggestRecruitPosts(userId);
            return ResponseEntity.ok().body(suggests);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/suggestRecruitPost") // 제안하기 버튼을 눌렀을 때, 누른 사용자가 팀장으로 속한 팀의 팀명 리스트를 보여줌
    public ResponseEntity<?> showMyRecruitPosts(@Auth int userId) {
        try {
            List<MyRecruitPostNameList> myRecruitPostNameList = teamService.showMyRecruitPostNameAsLeaderAndCompleteList(userId);
            return ResponseEntity.ok().body(myRecruitPostNameList);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    @PostMapping("/suggestRecruitPostEnd") // 팀명을 선택하면 제안당한 유저의 아이디와 recruitPostId를 저장
    public ResponseEntity<?> suggestMyRecruitPost(@RequestBody SuggestRequestDTO suggestRequestDTO) {
        try {
            suggestRecruitPostService.suggestRecruitPost(suggestRequestDTO);
            return ResponseEntity.ok().body(suggestRequestDTO.getUserId() + "Successfully Suggested");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
