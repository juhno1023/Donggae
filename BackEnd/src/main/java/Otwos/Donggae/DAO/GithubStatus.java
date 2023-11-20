package Otwos.Donggae.DAO;

import Otwos.Donggae.DAO.User.User;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "github_stats")
public class GithubStatus {

    @Id
    @Column(name = "github_id", nullable = false)
    private Long githubId;

    // 1:1 user
    @OneToOne
    @JoinColumn(name = "user_id")
    private User userId;

    @Column(name = "commit_num")
    private Integer commitNum;

    @Column(name = "issue_num")
    private Integer issueNum;

    @Column(name = "star_num")
    private Integer starNum;

    @Column(name = "pr_num")
    private Integer prNum;

    @Builder
    public GithubStatus(Long githubId, User userId, Integer commitNum, Integer issueNum, Integer starNum, Integer prNum){
        this.githubId = githubId;
        this.userId = userId;
        this.commitNum = commitNum;
        this.issueNum = issueNum;
        this.starNum = starNum;
        this.prNum = prNum;
    }
}
