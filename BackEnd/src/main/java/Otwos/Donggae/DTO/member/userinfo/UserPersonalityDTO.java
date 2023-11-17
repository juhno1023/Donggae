package Otwos.Donggae.DTO.member.userinfo;

import Otwos.Donggae.DAO.User.User;
import Otwos.Donggae.DAO.User.UserPersonality;
import Otwos.Donggae.Global.PersonalityEnum;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserPersonalityDTO {
    private int userId;
    private PersonalityEnum personality;

    public UserPersonality toEntity(User user) {
        return UserPersonality.builder()
                .userId(user)
                .personality(personality)
                .build();
    }
}
