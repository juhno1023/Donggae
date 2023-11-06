package Otwos.Donggae.DAO.User;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.List;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id", nullable = false)
    private int userId;

    @Column(name = "github_name", length = 30, nullable = false)
    private String githubName; // 깃허브 닉네임

    @Column(name = "self_intro")
    private String intro; // 자기소개

    @Column(name = "team_exp_count")
    private int teamExpCount; // 팀플 경험 횟수

    @Column(name = "leader_count")
    private int leaderCount; // 팀장 경험 횟수

    @Column(name = "baekjoon_rank", length = 20)
    private String boj_rank; // 백준 랭크

    @Column(name = "developer_test_score")
    private int devTestScore; // 역량 평가 총 점수

    @Column(name = "dgu_email", length = 30, nullable = false)
    private String dguEmail; // 동국대 이메일

    // 1:N userLanguage
    @OneToMany(mappedBy = "userId")
    private List<UserLanguage> userLanguages;

    // 1:N userInterestField
    @OneToMany(mappedBy = "userId")
    private List<UserInterestField> userInterestFields;

    // 1:N userPersonality
    @OneToMany(mappedBy = "userId")
    private List<UserPersonality> userPersonalities;

    // 1:N userStudyField
    @OneToMany(mappedBy = "userId")
    private List<UserStudyField> userStudyFields;
}
