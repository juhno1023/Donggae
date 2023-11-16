package Otwos.Donggae.DTO.member.userinfo.response;

import Otwos.Donggae.Global.FieldEnum;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserInterestFieldResponse {
    private FieldEnum interestField;
}
