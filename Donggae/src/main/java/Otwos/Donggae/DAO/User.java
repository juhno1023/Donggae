package Otwos.Donggae.DAO;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;

@Getter
@Entity
@Table(name = "user")
public class User {

    @Id
    @Column(name = "user_id", nullable = false)
    private String userId; // 깃허브 아이디

    @Column(name = "github_name", nullable = false)
    private String githubName; // 깃허브 닉네임

    @Column(name = "self_intro")
    private String intro; // 자기소개

    @Column(name = "team_exp_count")
    private int teamExpCount; // 팀플 경험 횟수

    @Column(name = "leader_count")
    private int leaderCount; // 팀장 경험 횟수

    @Column(name = "baekjoon_rank")
    private String boj_rank; // 백준 랭크

    @Column(name = "developer_test_score")
    private int devTestScore; // 역량 평가 총 점수

    @Column(name = "dgu_email")
    private String dguEmail; // 동국대 이메일
}
