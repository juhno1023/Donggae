package Otwos.Donggae.DTO.team.showMyTeam;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TeamByLeader {
    //팀장으로 속한 팀
    private int recruitPostId;
    private int teamId;
    private String teamName; //팀이름
    private String title; //프로젝트 제목
    private TeamMemberPreview teamMemberPreview; //팀장 정보
}
