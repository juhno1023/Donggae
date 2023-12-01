package Otwos.Donggae.DTO.member.userinfo;

import Otwos.Donggae.DAO.User.User;
import Otwos.Donggae.DAO.User.UserInterestField;
import Otwos.Donggae.Global.FieldEnum;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserInterestFieldDTO {
    private int userId;
    private String interestField;

    public UserInterestField toEntity(User user) {
        return UserInterestField.builder()
                .userId(user)
                .interestField(FieldEnum.valueOfLabel(interestField))
                .build();
    }
}
