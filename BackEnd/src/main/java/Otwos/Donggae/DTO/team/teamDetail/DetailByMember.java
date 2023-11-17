package Otwos.Donggae.DTO.team.teamDetail;

import Otwos.Donggae.DTO.team.showMyTeam.TeamMemberPreview;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class DetailByMember {
    private int teamId;
    private String title;
    private String teamName;
    private String content;
    private List<TeamMemberPreview> teamMemberList;
}
