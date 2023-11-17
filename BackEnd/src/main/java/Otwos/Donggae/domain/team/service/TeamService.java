package Otwos.Donggae.domain.team.service;

import Otwos.Donggae.DAO.Team.Team;
import Otwos.Donggae.DTO.RecruitPost.RecruitPostRequestDTO;
import Otwos.Donggae.DTO.team.TeamDTO;
import Otwos.Donggae.DTO.team.selectTeamMember.SelectTeamMemberRequest;
import Otwos.Donggae.DTO.team.showMyTeam.MyTeamList;
import Otwos.Donggae.DTO.team.teamDetail.DetailByMember;
import Otwos.Donggae.DTO.team.teamDetail.TeamIdRequest;

public interface TeamService {
    void selectTeamMember(SelectTeamMemberRequest request);

    void deleteTeamMember(SelectTeamMemberRequest request);
    MyTeamList showTeamS(int userId);
    DetailByMember DetailTeamByMember(TeamIdRequest teamIdRequest);
}
