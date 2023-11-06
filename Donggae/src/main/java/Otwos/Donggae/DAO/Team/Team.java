package Otwos.Donggae.DAO.Team;
import Otwos.Donggae.DAO.Recruit.RecruitPost;
import jakarta.persistence.*;
import lombok.Getter;

import java.util.List;

@Entity
@Table(name = "team")
@Getter
public class Team {

    @Id
    @Column(name = "team_id")
    private int teamId;

    @ManyToOne
    @JoinColumn(name = "recruit_post_id")
    private RecruitPost recruitPost;

    @Column(name = "team_name")
    private String teamName;

    @OneToMany(mappedBy = "team")
    private List<TeamMember> teamMembers;

    // Getters and setters
}
