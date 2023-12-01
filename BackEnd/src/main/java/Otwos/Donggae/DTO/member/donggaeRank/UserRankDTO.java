package Otwos.Donggae.DTO.member.donggaeRank;

import Otwos.Donggae.DAO.User.User;
import Otwos.Donggae.DAO.User.UserRank;
import Otwos.Donggae.Global.Rank.DonggaeRank;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserRankDTO {
    private int id;
    private int userId;
    private int score;
    private String donggaeRank;

    public UserRank toEntity(User user) {
        return UserRank.builder()
                .id(id)
                .user(user)
                .score(score)
                .rankName(DonggaeRank.valueOfLabel(donggaeRank))
                .build();
    }
}
