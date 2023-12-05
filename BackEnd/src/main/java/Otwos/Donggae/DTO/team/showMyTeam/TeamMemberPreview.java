package Otwos.Donggae.DTO.team.showMyTeam;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TeamMemberPreview {
    private int userId;
    private String name; //이름
    private String bojRank; //백준랭크
    private String donggaeRank; //동개랭크
    private Boolean isLeader; //팀장여부
    private String userProfile; //깃허브 프로필 사진
}
