package Otwos.Donggae.DTO.team;

import Otwos.Donggae.DAO.Recruit.RecruitPost;
import Otwos.Donggae.DAO.Team.Team;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TeamDTO {
    private int teamId;
    private int recruitPostId;
    private String teamName;

    public Team toEntity(RecruitPost recruitPost) {
        return Team.builder()
                .teamId(teamId)
                .recruitPost(recruitPost)
                .teamName(teamName)
                .build();
    }
}
