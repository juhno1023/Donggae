package Otwos.Donggae.DAO.User.Identifier;

import java.io.Serializable;
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
    private String interestField;
}
