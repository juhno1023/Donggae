package Otwos.Donggae.DTO.team.showMyTeam;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TeamByMember {
    //팀원으로 속한 팀
    private String teamName; //팀이름
    private String title; //프로젝트 제목
    private teamLeader teamLeader; //팀장 정보
}
