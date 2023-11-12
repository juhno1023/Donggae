package Otwos.Donggae.DTO.team.selectTeamMember;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SelectTeamMemberRequest {
    private int userId;
    private int teamId;
}
