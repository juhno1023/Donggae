package Otwos.Donggae.DAO;

import Otwos.Donggae.DAO.User.User;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "github_stats")
public class GithubStatus {

    @Id
    @Column(name = "github_id")
    private int githubId;

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
}
