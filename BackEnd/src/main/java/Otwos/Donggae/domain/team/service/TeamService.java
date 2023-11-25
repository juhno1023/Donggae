package Otwos.Donggae.domain.team.service;

import Otwos.Donggae.DTO.team.selectTeamMember.SelectTeamMemberRequest;
import Otwos.Donggae.DTO.team.showMyTeam.MyTeamList;
import Otwos.Donggae.DTO.team.showMyTeam.MyRecruitPostNameList;
import Otwos.Donggae.DTO.team.teamDetail.DetailByLeader;
import Otwos.Donggae.DTO.team.teamDetail.DetailByMember;
import Otwos.Donggae.DTO.team.teamDetail.TeamIdRequest;

import java.util.List;

public interface TeamService {
    void selectTeamMember(SelectTeamMemberRequest request);

    void deleteTeamMember(SelectTeamMemberRequest request);
    MyTeamList showTeamS(int userId);
    DetailByMember DetailTeamByMember(TeamIdRequest teamIdRequest);
    DetailByLeader DetailTeamByLeader(TeamIdRequest teamIdRequest);

    List<MyRecruitPostNameList> showMyRecruitPostNameAsLeaderAndCompleteList(int userId);
}
