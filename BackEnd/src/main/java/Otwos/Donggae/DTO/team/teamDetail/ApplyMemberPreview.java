package Otwos.Donggae.DTO.team.teamDetail;

import Otwos.Donggae.Global.Rank.BaekjoonRank;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ApplyMemberPreview {
    private int userId;
    private String name; //이름
    private BaekjoonRank bojRank; //백준랭크
    private String donggaeRank; //동개랭크
}
