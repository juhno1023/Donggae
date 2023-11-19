package Otwos.Donggae.domain.rank.controller;

import Otwos.Donggae.domain.rank.service.RankService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RankController {

    @Autowired
    private RankService rankService;

    @PostMapping("/members/rank")
    public ResponseEntity<?> getRankList(){
        try{
            // 전체 랭크 리스트 그대로 반환

            return ResponseEntity.ok().body("성공");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    // 매일 밤 12시에 랭크를 재할당하는 스케줄러 메서드
    @Scheduled(cron = "0 0 0 * * ?")
    public void calculateRank(){
        try{
            // 모든 유저의 랭크 점수 갱신
            int userCount = rankService.calAllUserRankScore();

            // 랭크 점수 순으로 정렬하여 랭크 재할당
            rankService.reallocRank(userCount);
            System.out.println("랭크 재할당 완료!!");
        } catch (Exception e) {
            System.out.println("e.getMessage() = " + e.getMessage());
        }
    }
}
