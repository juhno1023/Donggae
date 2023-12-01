package Otwos.Donggae.DTO.member.donggaeRank;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class UserRankInfoDTO {

    private int ranking;
    private String rankName;
    private String githubName;
    private List<String> userInterestFields;
    private int score;
    private String bojRank;

}
