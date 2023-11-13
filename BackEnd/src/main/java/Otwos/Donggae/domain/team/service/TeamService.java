package Otwos.Donggae.domain.team.service;

import Otwos.Donggae.DAO.Team.Team;
import Otwos.Donggae.DTO.RecruitPost.RecruitPostRequestDTO;
import Otwos.Donggae.DTO.team.TeamDTO;
import Otwos.Donggae.DTO.team.selectTeamMember.SelectTeamMemberRequest;

public interface TeamService {
    public void selectTeamMember(SelectTeamMemberRequest request);

    public void deleteTeamMember(SelectTeamMemberRequest request);
}
