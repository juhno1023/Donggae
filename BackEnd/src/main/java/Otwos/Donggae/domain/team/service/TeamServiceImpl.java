package Otwos.Donggae.domain.team.service;

import Otwos.Donggae.DAO.Recruit.RecruitPost;
import Otwos.Donggae.DAO.Team.Team;
import Otwos.Donggae.DAO.Team.TeamMember;
import Otwos.Donggae.DAO.User.User;
import Otwos.Donggae.DAO.User.UserRank;
import Otwos.Donggae.DTO.RecruitPost.RecruitPostRequestDTO;
import Otwos.Donggae.DTO.team.TeamDTO;
import Otwos.Donggae.DTO.team.TeamMemberDTO;
import Otwos.Donggae.DTO.team.selectTeamMember.SelectTeamMemberRequest;
import Otwos.Donggae.DTO.team.showMyTeam.MyTeamList;
import Otwos.Donggae.DTO.team.showMyTeam.TeamByLeader;
import Otwos.Donggae.DTO.team.showMyTeam.TeamByMember;
import Otwos.Donggae.DTO.team.showMyTeam.TeamLeader;
import Otwos.Donggae.Global.Rank.DonggaeRank;
import Otwos.Donggae.domain.RecruitPost.Repository.RecruitPostRepository;
import Otwos.Donggae.domain.member.repository.MemberRepository;
import Otwos.Donggae.domain.rank.repository.UserRankRepository;
import Otwos.Donggae.domain.team.repository.TeamMemberRepository;
import Otwos.Donggae.domain.team.repository.TeamRepository;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TeamServiceImpl implements TeamService{

    private TeamRepository teamRepository;
    private TeamMemberRepository teamMemberRepository;
    private MemberRepository memberRepository;
    private UserRankRepository userRankRepository;
    private RecruitPostRepository recruitPostRepository;

    @Autowired
    public TeamServiceImpl(TeamRepository teamRepository, TeamMemberRepository teamMemberRepository,
                           MemberRepository memberRepository, UserRankRepository userRankRepository,
                           RecruitPostRepository recruitPostRepository) {
        this.teamRepository = teamRepository;
        this.teamMemberRepository = teamMemberRepository;
        this.memberRepository = memberRepository;
        this.userRankRepository = userRankRepository;
        this.recruitPostRepository = recruitPostRepository;
    }

    //회원 뽑기
    //지원자 선택해서 팀에(팀원으로) 추가하기
    @Override
    public void selectTeamMember(SelectTeamMemberRequest request) {
        User user = memberRepository.findUserByUserId(request.getUserId());
        Team team = teamRepository.findTeamByTeamId(request.getTeamId());

        try {
            validateSelectRequest(user, team);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        TeamMemberDTO teamMemberDTO = new TeamMemberDTO(request.getTeamId(), request.getUserId(), Boolean.FALSE);
        teamMemberRepository.save(teamMemberDTO.toEntity(team, user));
    }

    //팀원 추방하기
    //teamMember에서 삭제
    @Transactional
    @Override
    public void deleteTeamMember(SelectTeamMemberRequest request) {
        User user = memberRepository.findUserByUserId(request.getUserId());
        Team team = teamRepository.findTeamByTeamId(request.getTeamId());

        try {
            validateDeleteRequest(user, team);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        //teamMember에서 삭제
        teamMemberRepository.deleteTeamMemberByTeamIdAndUserId(team, user);
    }

    //팀장으로 속한 팀, 팀원으로 속한 팀 show
    @Override
    public MyTeamList showTeamS(int userId) {
        User user = memberRepository.findUserByUserId(userId);

        List<TeamByLeader> leaders = new ArrayList<>();
        List<TeamByMember> members = new ArrayList<>();

        // userId에 해당하는 teamMember 모두 찾기 - team과 1:1매칭 가능
        List<TeamMember> teams = teamMemberRepository.findTeamMembersByUserId(user);

        for (TeamMember teamMember : teams) {

            if (teamMember.getIsLeader() == Boolean.TRUE) { //user가 팀장인 경우

                UserRank userRank = userRankRepository.findUserRankByUserId(user);
                DonggaeRank donggaeRank = userRank.getRankName();
                if (donggaeRank == null) { //동개랭크 없는경우
                    donggaeRank = DonggaeRank.똥개;
                }
                Team team = teamMember.getTeamId(); //해당하는 팀
                RecruitPost recruitPost = team.getRecruitPostId(); //해당하는 모집 글

                TeamLeader teamLeader = new TeamLeader(
                        user.getGithubName(), //팀장 이름
                        user.getBoj_rank(), //팀장 백준랭크
                        donggaeRank); //팀장 동개랭크

                TeamByLeader teamByLeader = new TeamByLeader(
                        team.getTeamName(), //팀 이름
                        recruitPost.getTitle(), //프로젝트 제목
                        teamLeader); //팀장 정보

                leaders.add(teamByLeader); //팀장으로 속한 팀 리스트에 추가

            } else { //user가 팀원인 경우
                UserRank userRank = userRankRepository.findUserRankByUserId(user);
                DonggaeRank donggaeRank = userRank.getRankName();
                if (donggaeRank == null) { //동개랭크 없는경우
                    donggaeRank = DonggaeRank.똥개;
                }
                Team team = teamMember.getTeamId(); //해당하는 팀
                RecruitPost recruitPost = team.getRecruitPostId(); //해당하는 모집 글

                TeamLeader teamLeader = new TeamLeader(
                        user.getGithubName(), //팀장 이름
                        user.getBoj_rank(), //팀장 백준랭크
                        donggaeRank); //팀장 동개랭크

                TeamByMember teamByMember = new TeamByMember(
                        team.getTeamName(), //팀 이름
                        recruitPost.getTitle(), //프로젝트 제목
                        teamLeader); //팀장 정보

                members.add(teamByMember); //팀원으로 속한 팀 리스트에 추가

            }
        }
        MyTeamList myTeamList = new MyTeamList(leaders, members);
        return myTeamList;
    }

    private void validateSelectRequest(User user, Team team) throws Exception{
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

    private void validateDeleteRequest(User user, Team team) throws Exception{
        TeamMember teamMember = teamMemberRepository.findTeamMemberByTeamIdAndUserId(team, user);
        if (user == null) {
            throw new Exception("존재하지 않는 user입니다.");
        }
        if (team == null) {
            throw new Exception("존재하지 않는 team입니다.");
        }
        if (teamMember == null) {
            throw new Exception("존재하지 않는 팀원입니다.");
        }
        if (teamMember.getIsLeader() == Boolean.TRUE) {
            throw new Exception("팀장은 추방 불가.");
        }
    }
}
