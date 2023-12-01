package Otwos.Donggae.DTO.member.userinfo;

import Otwos.Donggae.DAO.User.User;
import Otwos.Donggae.DAO.User.UserLanguage;
import Otwos.Donggae.Global.LanguageEnum;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserLanguageDTO {
    private int userId;
    private String language;

    public UserLanguage toEntity(User user) {
        return UserLanguage.builder()
                .userId(user)
                .language(LanguageEnum.valueOfLabel(language))
                .build();
    }
}
