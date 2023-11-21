package Otwos.Donggae.DTO.member.userinfo.response;

import Otwos.Donggae.Global.FieldEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserInterestFieldResponse {
    private FieldEnum interestField;
}
