package Otwos.Donggae.domain.RecruitPost.service;

import Otwos.Donggae.DAO.Recruit.RecruitPost;
import Otwos.Donggae.DAO.Suggest;
import Otwos.Donggae.DAO.Team.Team;
import Otwos.Donggae.DAO.Team.TeamMember;
import Otwos.Donggae.DAO.User.User;
import Otwos.Donggae.DAO.User.UserRank;
import Otwos.Donggae.DTO.RecruitPost.SuggestDTO;
import Otwos.Donggae.DTO.RecruitPost.SuggestRequestDTO;
import Otwos.Donggae.DTO.team.showMyTeam.TeamByMember;
import Otwos.Donggae.DTO.team.showMyTeam.TeamMemberPreview;
import Otwos.Donggae.domain.RecruitPost.Repository.RecruitPostRepository;
import Otwos.Donggae.domain.RecruitPost.Repository.SuggestRepository;
import Otwos.Donggae.domain.member.repository.MemberRepository;
import Otwos.Donggae.domain.rank.repository.UserRankRepository;
import Otwos.Donggae.domain.team.repository.TeamMemberRepository;
import Otwos.Donggae.domain.team.repository.TeamRepository;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static java.sql.Types.NULL;

@Service
public class SuggestRecruitPostServiceImpl implements SuggestRecruitPostService{

    @Autowired
    private RecruitPostRepository recruitPostRepository;
    @Autowired
    private MemberRepository memberRepository;
    @Autowired
    private SuggestRepository suggestRepository;
    @Autowired
    private TeamRepository teamRepository;
    @Autowired
    private TeamMemberRepository teamMemberRepository;
    @Autowired
    private UserRankRepository userRankRepository;

    @Override
    public List<TeamByMember> showSuggestRecruitPosts(int userId) {
        User user = memberRepository.findUserByUserId(userId);
        List<Suggest> suggests = suggestRepository.findAllByUserId(user);

        List<TeamByMember> suggestRecruitPosts = new ArrayList<>();

        for (Suggest suggest : suggests) {
            RecruitPost recruitPost = suggest.getRecruitPostId();
            Team team = teamRepository.findTeamByRecruitPostId(recruitPost);

            List<TeamMember> teamMembers = teamMemberRepository.findTeamMembersByTeamId(team);
            TeamMemberPreview teamMemberPreview = null;

            for (TeamMember teamMember1 : teamMembers) {
                if (teamMember1.getIsLeader() == Boolean.TRUE) {
                    teamMemberPreview = createTeamMemberPreview(teamMember1);
                }
            }

            TeamByMember teamByMember = new TeamByMember(
                    recruitPost.getRecruitPostId(),
                    team.getTeamId(),
                    team.getTeamName(), //팀 이름
                    recruitPost.getTitle(), //프로젝트 제목
                    teamMemberPreview); //팀장 정보

            suggestRecruitPosts.add(teamByMember);
        }

        return suggestRecruitPosts;
    }

    @Override
    public void suggestRecruitPost(SuggestRequestDTO suggestRequestDTO) {

        int userId = suggestRequestDTO.getUserId();
        User user = memberRepository.findUserByUserId(userId);

        int recruitPostId = suggestRequestDTO.getRecruitPostId();

        RecruitPost recruitPost = recruitPostRepository.findRecruitPostByRecruitPostId(recruitPostId);

        SuggestDTO suggestDTO = new SuggestDTO(
            NULL,
            user,
            recruitPost
        );
        suggestRepository.save(suggestDTO.toEntity(user,recruitPost));
    }

    private TeamMemberPreview createTeamMemberPreview(TeamMember teamMember) {
        User user = teamMember.getUserId();
        UserRank userRank = userRankRepository.findUserRankByUserId(user);

        return new TeamMemberPreview(
                user.getUserId(),
                user.getGithubName(),
                user.getBoj_rank(),
                userRank.getRankName(),
                teamMember.getIsLeader());
    }


}
