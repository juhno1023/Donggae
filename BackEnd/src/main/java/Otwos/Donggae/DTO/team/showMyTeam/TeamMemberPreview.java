package Otwos.Donggae.DTO.team.showMyTeam;

import Otwos.Donggae.Global.Rank.BaekjoonRank;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TeamMemberPreview {
    private int userId;
    private String name; //이름
    private BaekjoonRank bojRank; //백준랭크
    private String donggaeRank; //동개랭크
    private Boolean isLeader; //팀장여부
}
