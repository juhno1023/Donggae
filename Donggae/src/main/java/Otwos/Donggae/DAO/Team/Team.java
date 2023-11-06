package Otwos.Donggae.DAO.Team;
import Otwos.Donggae.DAO.Recruit.RecruitPost;
import jakarta.persistence.*;
import lombok.AccessLevel;
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
    @Column(name = "team_id")
    private int teamId;

    // 1:1 recruitPost
    @OneToOne
    @JoinColumn(name = "recruit_post_id")
    private RecruitPost recruitPostId;

    @Column(name = "team_name")
    private String teamName;

    // 1:N teamMember
    @OneToMany(mappedBy = "teamId")
    private List<TeamMember> teamMembers;

}
