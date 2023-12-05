package Otwos.Donggae.DTO.team;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class RecMemberDTO {

    private int userId;
    private String githubName; // 깃허브 닉네임

    private String intro; // 자기소개

    private int teamExpCount; // 팀플 경험 횟수

    private int leaderCount; // 팀장 경험 횟수

    private String boj_rank; // 백준 랭크

    private int devTestScore; // 역량 평가 총 점수

    private String userProfile;

    private List<String> userLanguages; //유저 언어

    private List<String> userPersonalities; //유저 성향

    private List<String> userInterestFields; //유저 관심 분야

    private List<String> userStudyFields; //수강한 강의
}
