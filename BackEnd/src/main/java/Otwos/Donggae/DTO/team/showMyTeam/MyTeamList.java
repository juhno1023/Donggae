package Otwos.Donggae.DTO.team.showMyTeam;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class MyTeamList {
    private List<TeamByLeader> teamByLeaders;
    private List<TeamByMember> teamByMembers;
}
