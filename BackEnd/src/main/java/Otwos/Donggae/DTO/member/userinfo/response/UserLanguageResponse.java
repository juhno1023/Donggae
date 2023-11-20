package Otwos.Donggae.DTO.member.userinfo.response;

import Otwos.Donggae.Global.LanguageEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserLanguageResponse {
    private LanguageEnum language;
}
