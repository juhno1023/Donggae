package Otwos.Donggae.DTO.team;

import Otwos.Donggae.DAO.Team.Team;
import Otwos.Donggae.DAO.Team.TeamMember;
import Otwos.Donggae.DAO.User.User;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TeamMemberDTO {
    private int teamId;
    private int userId;
    private Boolean isLeader;

    public TeamMember toEntity(Team team, User user) {
        return TeamMember.builder()
                .team(team)
                .user(user)
                .isLeader(isLeader)
                .build();
    }
}
