package Otwos.Donggae.DAO.User;

import Otwos.Donggae.Global.Rank.BaekjoonRank;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.List;
import java.util.Set;

import lombok.*;

@Getter
@Entity
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id", nullable = false)
    private int userId;

    @Column(name = "github_name", length = 200, nullable = false)
    private String githubName; // 깃허브 닉네임

    @Column(name = "self_intro")
    private String intro; // 자기소개

    @Column(name = "team_exp_count")
    private int teamExpCount; // 팀플 경험 횟수

    @Column(name = "leader_count")
    private int leaderCount; // 팀장 경험 횟수

    @Column(name = "baekjoon_rank", length = 200)
    @Enumerated(EnumType.STRING)
    private BaekjoonRank boj_rank; // 백준 랭크

    @Column(name = "developer_test_score")
    private int devTestScore; // 역량 평가 총 점수

    @Column(name = "dgu_email", length = 30, nullable = false)
    private String dguEmail; // 동국대 이메일

    @Column(name = "user_profile", nullable = false)
    private String userProfile;

    // 1:N userLanguage
    @OneToMany(mappedBy = "userId")
    private Set<UserLanguage> userLanguages;

    // 1:N userInterestField
    @OneToMany(mappedBy = "userId")
    private Set<UserInterestField> userInterestFields;

    // 1:N userPersonality
    @OneToMany(mappedBy = "userId")
    private Set<UserPersonality> userPersonalities;

    // 1:N userStudyField
    @OneToMany(mappedBy = "userId")
    private Set<UserStudyField> userStudyFields;

    @Builder
    public User(int userId, String githubName,
                String intro, int teamExpCount,
                int leaderCount, BaekjoonRank boj_rank,
                int devTestScore, String dguEmail) {
        this.userId = userId;
        this.githubName = githubName;
        this.intro = intro;
        this.teamExpCount = teamExpCount;
        this.leaderCount = leaderCount;
        this.boj_rank = boj_rank;
        this.devTestScore = devTestScore;
        this.dguEmail = dguEmail;
    }

    @Builder
    public User(int userId, String githubName,
                String intro, int teamExpCount,
                int leaderCount, BaekjoonRank boj_rank,
                int devTestScore, String dguEmail, Set<UserLanguage> userLanguages,
                Set<UserInterestField> userInterestFields, Set<UserPersonality> userPersonalities
                ) {

        this.userId = userId;
        this.githubName = githubName;
        this.intro = intro;
        this.teamExpCount = teamExpCount;
        this.leaderCount = leaderCount;
        this.boj_rank = boj_rank;
        this.devTestScore = devTestScore;
        this.dguEmail = dguEmail;
        this.userLanguages = userLanguages;
        this.userInterestFields = userInterestFields;
        this.userPersonalities = userPersonalities;
    }
}
