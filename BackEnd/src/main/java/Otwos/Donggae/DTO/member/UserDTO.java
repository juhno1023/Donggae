package Otwos.Donggae.DTO.member;

import Otwos.Donggae.DAO.User.User;
import Otwos.Donggae.Global.Rank.BaekjoonRank;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserDTO {
    private int userId;
    private String githubName;
    private String intro;
    private int teamExpCount;
    private int leaderCount;
    private String bojRank;
    private int devTestScore;
    private String dguEmail;

    public User toEntity() {
        return User.builder()
                .userId(userId)
                .githubName(githubName)
                .intro(intro)
                .teamExpCount(teamExpCount)
                .leaderCount(leaderCount)
                .boj_rank(BaekjoonRank.valueOfLabel(bojRank))
                .devTestScore(devTestScore)
                .dguEmail(dguEmail)
                .build();
    }
}
