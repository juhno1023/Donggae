package Otwos.Donggae.DTO.member.userinfo.response;

import Otwos.Donggae.Global.StudyFieldEnum;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserStudyFieldResponse {
    private StudyFieldEnum studyField;
}
