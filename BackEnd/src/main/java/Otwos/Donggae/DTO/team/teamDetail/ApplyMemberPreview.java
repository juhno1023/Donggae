package Otwos.Donggae.DTO.team.teamDetail;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ApplyMemberPreview {
    private int userId;
    private String name; //이름
    private String bojRank; //백준랭크
    private String donggaeRank; //동개랭크
}
