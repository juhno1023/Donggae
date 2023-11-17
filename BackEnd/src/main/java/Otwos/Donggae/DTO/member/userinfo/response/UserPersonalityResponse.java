package Otwos.Donggae.DTO.member.userinfo.response;

import Otwos.Donggae.Global.PersonalityEnum;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserPersonalityResponse {
    private PersonalityEnum personality;
}
