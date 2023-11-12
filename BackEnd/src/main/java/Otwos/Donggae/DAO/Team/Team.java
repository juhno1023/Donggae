package Otwos.Donggae.DAO.Team;
import Otwos.Donggae.DAO.Recruit.RecruitPost;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;

import java.util.List;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "team")
@Getter
public class Team {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "team_id", nullable = false)
    private int teamId;

    // 1:1 recruitPost
    @OneToOne
    @JoinColumn(name = "recruit_post_id")
    private RecruitPost recruitPostId;

    @Column(name = "team_name", length = 30, nullable = false)
    private String teamName;

    // 1:N teamMember
    @OneToMany(mappedBy = "teamId")
    private List<TeamMember> teamMembers;

    @Builder
    public Team(int teamId, RecruitPost recruitPost,
                String teamName) {
        this.teamId = teamId;
        this.recruitPostId = recruitPost;
        this.teamName = teamName;
    }
}
