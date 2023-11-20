package Otwos.Donggae.DAO.User.Identifier;

import java.io.Serializable;

import Otwos.Donggae.DAO.User.User;
import Otwos.Donggae.Global.FieldEnum;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class UserInterestFieldPK implements Serializable {
    private int userId;
    private FieldEnum interestField;
}
