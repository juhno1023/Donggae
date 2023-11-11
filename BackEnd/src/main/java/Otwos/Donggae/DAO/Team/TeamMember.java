package Otwos.Donggae.DAO.Team;

import Otwos.Donggae.DAO.Team.Identifier.TeamMemberPK;
import Otwos.Donggae.DAO.User.User;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;

import java.io.Serializable;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "team_member")
@IdClass(TeamMemberPK.class)
@Getter
public class TeamMember {

    // N:1 team
    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "team_id")
    private Team teamId;

    // N:1 user
    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User userId;

    @Column(name = "is_leader")
    private Boolean isLeader;

}

