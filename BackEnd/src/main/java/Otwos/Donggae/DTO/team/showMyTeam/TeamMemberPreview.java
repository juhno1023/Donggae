package Otwos.Donggae.DTO.team.showMyTeam;

import Otwos.Donggae.Global.Rank.BaekjoonRank;
import Otwos.Donggae.Global.Rank.DonggaeRank;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TeamMemberPreview {
    //팀장
    private String name; //팀장 이름
    private BaekjoonRank bojRank; //팀장 백준랭크
    private DonggaeRank donggaeRank; //팀장 동개랭크
    private Boolean isLeader; //팀장여부
}
