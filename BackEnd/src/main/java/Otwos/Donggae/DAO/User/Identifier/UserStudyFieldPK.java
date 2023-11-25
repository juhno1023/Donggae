package Otwos.Donggae.DAO.User.Identifier;

import java.io.Serializable;

import Otwos.Donggae.Global.StudyFieldEnum;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

//필요 없어짐
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class UserStudyFieldPK implements Serializable {
    private int userId;
    private StudyFieldEnum studyField;
}
