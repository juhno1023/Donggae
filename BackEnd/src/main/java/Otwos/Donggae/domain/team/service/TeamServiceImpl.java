package Otwos.Donggae.domain.team.service;

import Otwos.Donggae.DAO.Team.Team;
import Otwos.Donggae.DAO.Team.TeamMember;
import Otwos.Donggae.DAO.User.User;
import Otwos.Donggae.DTO.team.TeamMemberDTO;
import Otwos.Donggae.DTO.team.selectTeamMember.SelectTeamMemberRequest;
import Otwos.Donggae.domain.member.repository.MemberRepository;
import Otwos.Donggae.domain.team.repository.TeamMemberRepository;
import Otwos.Donggae.domain.team.repository.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TeamServiceImpl implements TeamService{

    private TeamRepository teamRepository;
    private TeamMemberRepository teamMemberRepository;
    private MemberRepository memberRepository;

    @Autowired
    public TeamServiceImpl(TeamRepository teamRepository, TeamMemberRepository teamMemberRepository,
                           MemberRepository memberRepository) {
        this.teamRepository = teamRepository;
        this.teamMemberRepository = teamMemberRepository;
        this.memberRepository = memberRepository;
    }

    //회원 뽑기
    //지원자 선택해서 팀에(팀원으로) 추가하기
    @Override
    public void selectTeamMember(SelectTeamMemberRequest request) {
        User user = memberRepository.findUserByUserId(request.getUserId());
        Team team = teamRepository.findTeamByTeamId(request.getTeamId());

        try {
            validateRequest(user, team);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        TeamMemberDTO teamMemberDTO = new TeamMemberDTO(request.getTeamId(), request.getUserId(), Boolean.FALSE);
        teamMemberRepository.save(teamMemberDTO.toEntity(team, user));
    }

    private void validateRequest(User user, Team team) throws Exception{
        TeamMember teamMember = teamMemberRepository.findTeamMemberByTeamIdAndUserId(team, user);
        if (user == null) {
            throw new Exception("존재하지 않는 user입니다.");
        }
        if (team == null) {
            throw new Exception("존재하지 않는 team입니다.");
        }
        if (teamMember != null) {
            throw new Exception("이미 팀에 존재하는 팀원입니다.");
        }
    }
}
