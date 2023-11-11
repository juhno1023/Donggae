package Otwos.Donggae.DTO.member.userinfo;

import Otwos.Donggae.DAO.User.User;
import Otwos.Donggae.DAO.User.UserStudyField;
import Otwos.Donggae.Global.StudyFieldEnum;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserStudyFieldDTO {
    private int userId;
    private StudyFieldEnum studyField;

    public UserStudyField toEntity(User user) {
        return UserStudyField.builder()
                .userId(user)
                .studyField(studyField)
                .build();
    }
}
