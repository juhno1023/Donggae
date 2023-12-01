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
    private String personality;

    public UserPersonality toEntity(User user) {
        return UserPersonality.builder()
                .userId(user)
                .personality(PersonalityEnum.valueOfLabel(personality))
                .build();
    }
}
