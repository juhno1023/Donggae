package Otwos.Donggae.DTO.member.userinfo.response;

import Otwos.Donggae.Global.LanguageEnum;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserLanguageResponse {
    private LanguageEnum language;
}
