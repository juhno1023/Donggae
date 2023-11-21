package Otwos.Donggae.DTO.member.userinfo.response;

import Otwos.Donggae.Global.PersonalityEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserPersonalityResponse {
    private PersonalityEnum personality;
}
