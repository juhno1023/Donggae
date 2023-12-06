package Otwos.Donggae.domain.team.service;

import Otwos.Donggae.DAO.Application;
import Otwos.Donggae.DAO.Recruit.RecruitPost;
import Otwos.Donggae.DAO.Team.Team;
import Otwos.Donggae.DAO.Team.TeamMember;
import Otwos.Donggae.DAO.User.User;
import Otwos.Donggae.DAO.User.UserRank;
import Otwos.Donggae.DTO.team.TeamMemberDTO;
import Otwos.Donggae.DTO.team.selectTeamMember.SelectTeamMemberRequest;
import Otwos.Donggae.DTO.team.showMyTeam.*;
import Otwos.Donggae.DTO.team.teamDetail.ApplyMemberPreview;
import Otwos.Donggae.DTO.team.teamDetail.DetailByLeader;
import Otwos.Donggae.DTO.team.teamDetail.DetailByMember;
import Otwos.Donggae.DTO.team.teamDetail.TeamIdRequest;
import Otwos.Donggae.domain.application.repository.ApplicationRepository;
import Otwos.Donggae.domain.member.repository.MemberRepository;
import Otwos.Donggae.domain.rank.repository.UserRankRepository;
import Otwos.Donggae.domain.team.repository.TeamMemberRepository;
import Otwos.Donggae.domain.team.repository.TeamRepository;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TeamServiceImpl implements TeamService{

    @Autowired
    private TeamRepository teamRepository;
    @Autowired
    private TeamMemberRepository teamMemberRepository;
    @Autowired
    private MemberRepository memberRepository;
    @Autowired
    private UserRankRepository userRankRepository;
    @Autowired
    private ApplicationRepository applicationRepository;


    //회원 뽑기
    //지원자 선택해서 팀에(팀원으로) 추가하기 -> 지원자에서 삭제
    @Transactional
    @Override
    public void selectTeamMember(SelectTeamMemberRequest request) {
        User user = memberRepository.findUserByUserId(request.getUserId());
        Team team = teamRepository.findTeamByTeamId(request.getTeamId());

        try {
            validateTeam(team);
            validateSelectRequest(user, team);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        TeamMemberDTO teamMemberDTO = new TeamMemberDTO(request.getTeamId(), request.getUserId(), Boolean.FALSE);
        teamMemberRepository.save(teamMemberDTO.toEntity(team, user));

        //지원자에서 삭제
        RecruitPost recruitPost = team.getRecruitPostId();
        Application application = applicationRepository.findApplicationByUserIdAndRecruitPostId(user, recruitPost);
        applicationRepository.delete(application);
    }

    //팀원 추방하기
    //teamMember에서 삭제
    @Transactional
    @Override
    public void deleteTeamMember(SelectTeamMemberRequest request) {
        User user = memberRepository.findUserByUserId(request.getUserId());
        Team team = teamRepository.findTeamByTeamId(request.getTeamId());

        try {
            validateTeam(team);
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

                Team team = teamMember.getTeamId(); //해당하는 팀
                RecruitPost recruitPost = team.getRecruitPostId(); //해당하는 모집 글

                TeamMemberPreview teamMemberPreview = new TeamMemberPreview(
                        userId,
                        user.getGithubName(), //팀장 이름
                        user.getBoj_rank().label(), //팀장 백준랭크
                        userRank.getRankName().label(), //팀장 동개랭크
                        teamMember.getIsLeader(),
                        user.getUserProfile());

                TeamByLeader teamByLeader = new TeamByLeader(
                        recruitPost.getRecruitPostId(),
                        team.getTeamId(),
                        team.getTeamName(), //팀 이름
                        recruitPost.getTitle(), //프로젝트 제목
                        teamMemberPreview); //팀장 정보

                leaders.add(teamByLeader); //팀장으로 속한 팀 리스트에 추가

            } else { //user가 팀원인 경우
                Team team = teamMember.getTeamId(); //해당하는 팀
                RecruitPost recruitPost = team.getRecruitPostId(); //해당하는 모집 글

                List<TeamMember> teamMembers = teamMemberRepository.findTeamMembersByTeamId(team);
                TeamMemberPreview teamMemberPreview = null;

                for (TeamMember teamMember1 : teamMembers) {
                    if (teamMember1.getIsLeader() == Boolean.TRUE) {
                        User user1 = teamMember1.getUserId();

                        UserRank userRank = userRankRepository.findUserRankByUserId(user1);

                        teamMemberPreview = new TeamMemberPreview(
                                user1.getUserId(),
                                user1.getGithubName(), //팀장 이름
                                user1.getBoj_rank().label(), //팀장 백준랭크
                                userRank.getRankName().label(), //팀장 동개랭크
                                teamMember1.getIsLeader(),
                                user1.getUserProfile());
                    }
                }

                TeamByMember teamByMember = new TeamByMember(
                        recruitPost.getRecruitPostId(),
                        team.getTeamId(),
                        team.getTeamName(), //팀 이름
                        recruitPost.getTitle(), //프로젝트 제목
                        teamMemberPreview); //팀장 정보

                members.add(teamByMember); //팀원으로 속한 팀 리스트에 추가

            }
        }
        return new MyTeamList(leaders, members);
    }

    //팀원으로 속한 팀 상세보기
    @Override
    public DetailByMember DetailTeamByMember(TeamIdRequest teamIdRequest) {
        Team team = teamRepository.findTeamByTeamId(teamIdRequest.getTeamId()); //해당하는 팀
        try {
            validateTeam(team);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        RecruitPost recruitPost = team.getRecruitPostId(); //해당하는 모집 글

        List<TeamMember> teams = teamMemberRepository.findTeamMembersByTeamId(team);
        List<TeamMemberPreview> teamMemberPreviews = new ArrayList<>();

        for (TeamMember teamMember : teams) { //팀원 리스트 반환
            User user = teamMember.getUserId();
            UserRank userRank = userRankRepository.findUserRankByUserId(user);

            TeamMemberPreview teamMemberPreview = new TeamMemberPreview(
                    user.getUserId(),
                    user.getGithubName(), //이름
                    user.getBoj_rank().label(), //백준랭크
                    userRank.getRankName().label(), //동개랭크
                    teamMember.getIsLeader(),
                    user.getUserProfile());
            teamMemberPreviews.add(teamMemberPreview);
        }

        return new DetailByMember(
                teamIdRequest.getTeamId(),
                recruitPost.getTitle(),
                team.getTeamName(),
                recruitPost.getContent(),
                teamMemberPreviews
        );
    }

    //팀장으로 속한 팀 상세보기
    @Override
    public DetailByLeader DetailTeamByLeader(TeamIdRequest teamIdRequest) {
        Team team = teamRepository.findTeamByTeamId(teamIdRequest.getTeamId()); //해당하는 팀
        try {
            validateTeam(team);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        RecruitPost recruitPost = team.getRecruitPostId(); //해당하는 모집 글

        List<TeamMember> teams = teamMemberRepository.findTeamMembersByTeamId(team);
        List<TeamMemberPreview> teamMemberList = new ArrayList<>();

        for (TeamMember teamMember : teams) { //팀원 리스트 반환
            User user = teamMember.getUserId();
            UserRank userRank = userRankRepository.findUserRankByUserId(user);

            TeamMemberPreview teamMemberPreview = new TeamMemberPreview(
                    user.getUserId(),
                    user.getGithubName(), //이름
                    user.getBoj_rank().label(), //백준랭크
                    userRank.getRankName().label(), //동개랭크
                    teamMember.getIsLeader(),
                    user.getUserProfile());
            teamMemberList.add(teamMemberPreview);
        }

        //지원자 리스트
        List<Application> applications = applicationRepository.findApplicationsByRecruitPostId(recruitPost);
        HashMap<Application, Integer> priorityMap = new HashMap<>();

        for (Application application : applications) { //지원자 리스트 반환 -> 지원자 우선순위 해시맵 반환
            User user = application.getUserId();

            int priority = calculatePriorityForMatchingAttributes(recruitPost, user);
            priorityMap.put(application, priority);
        }

        List<Application> applicationPriority = sortApplicationByPriority(priorityMap);

        List<ApplyMemberPreview> applyMemberList = new ArrayList<>();
        hashToDTO(applicationPriority, applyMemberList);

        return new DetailByLeader(
                teamIdRequest.getTeamId(),
                recruitPost.getTitle(),
                team.getTeamName(),
                recruitPost.getContent(),
                teamMemberList,
                applyMemberList
        );
    }

    // dto로 변환 (지원자 리스트만)
    private void hashToDTO(List<Application> applications, List<ApplyMemberPreview> applyMemberPreviews) {
        for (Application application : applications) {
            User user = application.getUserId();
            UserRank userRank = userRankRepository.findUserRankByUserId(user);

            ApplyMemberPreview teamMemberPreview = new ApplyMemberPreview(
                    user.getUserId(),
                    user.getGithubName(), //이름
                    user.getBoj_rank().label(), //백준랭크
                    userRank.getRankName().label() //동개랭크
            );
            applyMemberPreviews.add(teamMemberPreview);
        }
    }

    private int calculatePriorityForMatchingAttributes(RecruitPost post, User user) { //지원자 우선순위
        // user 정보 가져오기 - 관심분야, 언어, 성격
        Set<String> userInterestFieldList = user.getUserInterestFields()
                .stream()
                .map(userInterestField -> userInterestField.getInterestField().name())
                .collect(Collectors.toSet());
        Set<String> userLanguageList = user.getUserLanguages()
                .stream()
                .map(userLanguage -> userLanguage.getLanguage().name())
                .collect(Collectors.toSet());

        Set<String> userPersonalityList = user.getUserPersonalities()
                .stream()
                .map(userPersonality -> userPersonality.getPersonality().name())
                .collect(Collectors.toSet());

        int priority = 0;
        // 언어 일치 여부에 대한 점수 계산 (일치 개수만큼 우선순위 +1)
        long languageMatchCount = post.getRecruitLanguages().stream()
                .filter(lang -> userLanguageList.contains(lang.getLanguage().name()))
                .count();
        priority += languageMatchCount;

        // 관심 분야 일치 여부에 대한 점수 계산 (일치 개수만큼 우선순위 +1)
        long fieldMatchCount = post.getRecruitFields().stream()
                .filter(field -> userInterestFieldList.contains(field.getField().name()))
                .count();
        priority += fieldMatchCount;

        // 성격 특성 일치 여부에 대한 점수 계산 (일치 개수만큼 우선순위 +1)
        long personalityMatchCount = post.getRecruitPersonalities().stream()
                .filter(pers -> userPersonalityList.contains(pers.getPersonality().name()))
                .count();
        priority += personalityMatchCount;

        return priority;
    }

    //우선순위 순으로 정렬
    private List<Application> sortApplicationByPriority(HashMap<Application, Integer> priorityMap) {
        return priorityMap.entrySet()
                .stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());
    }

    private void validateSelectRequest(User user, Team team) throws Exception{
        validateUser(user);
        TeamMember teamMember = teamMemberRepository.findTeamMemberByTeamIdAndUserId(team, user);
        if (teamMember != null) {
            throw new Exception("this teamMember already exist in the team");
        }
    }

    private void validateDeleteRequest(User user, Team team) throws Exception{
        validateUser(user);
        TeamMember teamMember = teamMemberRepository.findTeamMemberByTeamIdAndUserId(team, user);
        if (teamMember == null) {
            throw new Exception("this teamMember doesn't exist");
        }
        if (Boolean.TRUE.equals(teamMember.getIsLeader())) {
            throw new Exception("can't delete leader");
        }
    }

    private void validateTeam(Team team) throws Exception {
        if (team == null) {
            throw new Exception("this team doesn't exist");
        }
    }

    private void validateUser(User user) throws Exception {
        if (user == null) {
            throw new Exception("this user doesn't exist");
        }
    }


    @Override
    public List<MyRecruitPostNameList> showMyRecruitPostNameAsLeaderAndCompleteList(int userId) {

        User user = memberRepository.findUserByUserId(userId);

        List<MyRecruitPostNameList> myRecruitPostNameList = new ArrayList<>();

        // userId에 해당하는 teamMember 모두 찾기 - team과 1:1매칭 가능
        List<TeamMember> teams = teamMemberRepository.findTeamMembersByUserId(user);

        for (TeamMember teamMember : teams) {

            if ((teamMember.getIsLeader() == Boolean.TRUE) && (teamMember.getUserId() == user)) { // user가 팀장인 경우

                Team team = teamMember.getTeamId(); //해당하는 팀
                RecruitPost recruitPost = team.getRecruitPostId(); //해당하는 모집 글

                if(recruitPost.getIsComplete() == Boolean.FALSE){ // 아직 모집이 끝나지 않은 팀들만 제안할 수 있도록!
                    MyRecruitPostNameList myRecruitPostNameList1 = new MyRecruitPostNameList(
                            recruitPost.getRecruitPostId(),
                            recruitPost.getTitle()
                    );
                    myRecruitPostNameList.add(myRecruitPostNameList1);
                }
            }
        }
        return myRecruitPostNameList;
    }
}
