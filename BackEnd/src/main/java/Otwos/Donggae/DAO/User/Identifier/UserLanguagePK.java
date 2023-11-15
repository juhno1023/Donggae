package Otwos.Donggae.DAO.User.Identifier;

import java.io.Serializable;

import Otwos.Donggae.Global.LanguageEnum;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class UserLanguagePK implements Serializable {
    private int userId;
    private LanguageEnum language;
}
