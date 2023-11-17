package Otwos.Donggae.DTO.team.selectTeamMember;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SelectTeamMemberRequest {
    private int userId;
    private int teamId;
}
