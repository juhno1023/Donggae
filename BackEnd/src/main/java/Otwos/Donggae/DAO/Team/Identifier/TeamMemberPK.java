package Otwos.Donggae.DAO.Team.Identifier;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class TeamMemberPK implements Serializable {
    private int teamId;
    private int userId;
}
