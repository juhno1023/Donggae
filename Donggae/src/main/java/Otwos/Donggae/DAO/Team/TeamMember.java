package Otwos.Donggae.DAO.Team;
import Otwos.Donggae.DAO.User.User;
import jakarta.persistence.*;
import lombok.Getter;

import java.io.Serializable;

@Entity
@Table(name = "team_member")
@Getter
public class TeamMember {

    @EmbeddedId
    private TeamMemberId id;

    @ManyToOne
    @MapsId("teamId")
    @JoinColumn(name = "team_id")
    private Team team;

    @ManyToOne
    @MapsId("userId")
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "is_leader")
    private Boolean isLeader;
    @Embeddable
    public class TeamMemberId implements Serializable {

        @Column(name = "team_id")
        private int teamId;

        @Column(name = "user_id")
        private int userId;
    }
}

