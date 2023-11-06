package Otwos.Donggae.DAO;

import Otwos.Donggae.DAO.User.User;
import jakarta.persistence.*;
import lombok.Getter;

@Getter
@Entity
@Table(name = "github_stats")
public class GithubStatus {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "commit_num")
    private Integer commitNum;

    @Column(name = "issue_num")
    private Integer issueNum;

    @Column(name = "star_num")
    private Integer starNum;

    @Column(name = "pr_num")
    private Integer prNum;

    // Constructors, getters, and setters
}
