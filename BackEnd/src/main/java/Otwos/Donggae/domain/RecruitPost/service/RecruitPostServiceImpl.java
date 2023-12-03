package Otwos.Donggae.domain.RecruitPost.service;

import Otwos.Donggae.DAO.Recruit.RecruitField;
import Otwos.Donggae.DAO.Recruit.RecruitLanguage;
import Otwos.Donggae.DAO.Recruit.RecruitPersonality;
import Otwos.Donggae.DAO.Recruit.RecruitPost;
import Otwos.Donggae.DAO.Team.Team;
import Otwos.Donggae.DAO.Team.TeamMember;
import Otwos.Donggae.DAO.User.*;
import Otwos.Donggae.DTO.RecruitPost.*;
import Otwos.Donggae.DTO.RecruitPost.recruitPostInfo.RecruitFieldDTO;
import Otwos.Donggae.DTO.RecruitPost.recruitPostInfo.RecruitLanguageDTO;
import Otwos.Donggae.DTO.RecruitPost.recruitPostInfo.RecruitPersonalityDTO;
import Otwos.Donggae.DTO.RecruitPost.recruitPostInfo.response.RecruitFieldResponse;
import Otwos.Donggae.DTO.RecruitPost.recruitPostInfo.response.RecruitLanguageResponse;
import Otwos.Donggae.DTO.RecruitPost.recruitPostInfo.response.RecruitPersonalityResponse;
import Otwos.Donggae.DTO.member.userinfo.response.UserInterestFieldResponse;
import Otwos.Donggae.DTO.member.userinfo.response.UserLanguageResponse;
import Otwos.Donggae.DTO.member.userinfo.response.UserPersonalityResponse;
import Otwos.Donggae.DTO.member.userinfo.response.UserStudyFieldResponse;
import Otwos.Donggae.DTO.team.TeamDTO;
import Otwos.Donggae.DTO.team.TeamMemberDTO;
import Otwos.Donggae.DTO.team.teamDetail.TeamIdRequest;
import Otwos.Donggae.Global.FieldEnum;
import Otwos.Donggae.Global.MajorLectureEnum;
import Otwos.Donggae.Global.PersonalityEnum;
import Otwos.Donggae.Global.Rank.DonggaeRank;
import Otwos.Donggae.domain.RecruitPost.Repository.RecruitPostRepository;
import Otwos.Donggae.domain.RecruitPost.Repository.info.RecruitFieldRepository;
import Otwos.Donggae.domain.RecruitPost.Repository.info.RecruitLanguageRepository;
import Otwos.Donggae.domain.RecruitPost.Repository.info.RecruitPersonalityRepository;
import Otwos.Donggae.domain.member.repository.MemberRepository;
import Otwos.Donggae.domain.member.repository.info.UserInterestFieldRepository;
import Otwos.Donggae.domain.member.repository.info.UserLanguageRepository;
import Otwos.Donggae.domain.member.repository.info.UserPersonalityRepository;
import Otwos.Donggae.domain.member.repository.info.UserStudyFieldRepository;
import Otwos.Donggae.domain.rank.repository.UserRankRepository;
import Otwos.Donggae.domain.team.repository.TeamMemberRepository;
import Otwos.Donggae.domain.team.repository.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static java.sql.Types.NULL;


@Service
public class RecruitPostServiceImpl implements RecruitPostService {
    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private RecruitPostRepository recruitPostRepository;

    @Autowired
    private RecruitLanguageRepository recruitLanguageRepository;

    @Autowired
    private RecruitPersonalityRepository recruitPersonalityRepository;
    @Autowired
    private RecruitFieldRepository recruitFieldRepository;

    @Autowired
    private TeamRepository teamRepository;

    @Autowired
    private TeamMemberRepository teamMemberRepository;

    @Autowired
    UserRankRepository userRankRepository;

    @Autowired
    UserLanguageRepository userLanguageRepository;

    @Autowired
    UserInterestFieldRepository userInterestFieldRepository;

    @Autowired
    UserPersonalityRepository userPersonalityRepository;

    @Autowired
    UserStudyFieldRepository userStudyFieldRepository;

    @Transactional //게시글 작성
    public void createRecruitPostAndTeam(RecruitPostRequestDTO recruitPostRequestDTO, int userId) {

        User user = memberRepository.findUserByUserId(userId);
        String title = recruitPostRequestDTO.getTitle();
        String content = recruitPostRequestDTO.getContent();
        Timestamp createdDate = new Timestamp(System.currentTimeMillis());
        String majorLectureName = recruitPostRequestDTO.getMajorLectureName();
        String teamName = recruitPostRequestDTO.getTeamName();

        RecruitPostDTO recruitPostDTO = new RecruitPostDTO(
                NULL,
                user,
                title,
                content,
                majorLectureName,
                createdDate,
                Boolean.FALSE,
                null,
                null,
                null,
                null
        );

        String tempTitle = title;

        recruitPostRepository.save(recruitPostDTO.toEntity());

        RecruitPost recruitPost = recruitPostRepository.findRecruitPostByTitle(tempTitle);

        List<RecruitLanguageDTO> recruitLanguageDTOS = addRecruitPostLanguageDTO(recruitPost, recruitPostRequestDTO);
        //게시글 모집 언어를 추가하기위한 DTO 리스트

        List<RecruitLanguage> recruitLanguages = convertToRecruitLanguageEntities(recruitLanguageDTOS, tempTitle);
        //게시글 모집 언어를 추가하기위한 DTO 리스트를 엔티티로 변환

        List<RecruitFieldDTO> recruitFieldDTOS = addRecruitPostFieldDTO(recruitPost, recruitPostRequestDTO);
        //게시글 모집 분야를 추가하기위한 DTO 리스트

        List<RecruitField> recruitFields = convertToRecruitFieldEntities(recruitFieldDTOS, tempTitle);
        //게시글 모집 언어를 추가하기위한 DTO 리스트를 엔티티로 변환

        List<RecruitPersonalityDTO> recruitPersonalityDTOS = addRecruitPostPersonalityDTO(recruitPost, recruitPostRequestDTO);
        //게시글 모집 성향를 추가하기위한 DTO 리스트

        List<RecruitPersonality> recruitPersonalities = convertToRecruitPersonalityEntities(recruitPersonalityDTOS, tempTitle);
        //게시글 모집 언어를 추가하기위한 DTO 리스트를 엔티티로 변환

        recruitLanguageRepository.saveAll(recruitLanguages);
        //저장
        recruitFieldRepository.saveAll(recruitFields);
        //저장
        recruitPersonalityRepository.saveAll(recruitPersonalities);
        //저장

        int recruitPostId = recruitPost.getRecruitPostId();

        TeamDTO teamDTO = new TeamDTO(
                NULL,
                recruitPostId,
                teamName
        );
        // Team 저장
        teamRepository.save(teamDTO.toEntity(recruitPost));

        Team team = teamRepository.findTeamByTeamName(teamName);

        TeamMemberDTO teamMemberDTO = new TeamMemberDTO(
                team.getTeamId(),
                user.getUserId(),
                Boolean.TRUE
        );
        // Team Member 저장
        teamMemberRepository.save(teamMemberDTO.toEntity(team, user));
    }

    private List<RecruitLanguageDTO> addRecruitPostLanguageDTO(RecruitPost recruitPost, RecruitPostRequestDTO recruitPostRequestDTO){
        List<RecruitLanguageDTO> recruitLanguageDTOS = new ArrayList<>(); //빈 리스트 생성
        List<String> recruitLanguages = recruitPostRequestDTO.getRecruitLanguages();

        for (String language : recruitLanguages) {
            RecruitLanguageDTO recruitLanguageDTO = new RecruitLanguageDTO(
                    recruitPost.getRecruitPostId(),
                    language
            );
            recruitLanguageDTOS.add(recruitLanguageDTO);
        }
        return recruitLanguageDTOS;
    }

    private List<RecruitLanguage> convertToRecruitLanguageEntities(List<RecruitLanguageDTO> recruitLanguageDTOS, String title) {
        return recruitLanguageDTOS.stream()
                .map(dto -> {
                    RecruitPost recruitPost = recruitPostRepository.findRecruitPostByTitle(title);
                    return new RecruitLanguage(recruitPost, dto.getLanguage());
                })
                .collect(Collectors.toList());
    }

    private List<RecruitFieldDTO> addRecruitPostFieldDTO(RecruitPost recruitPost, RecruitPostRequestDTO recruitPostRequestDTO){
        List<RecruitFieldDTO> recruitFieldDTOS = new ArrayList<>(); //빈 리스트 생성
        List<String> recruitFields = recruitPostRequestDTO.getRecruitFields();

        for (String field : recruitFields) {
            RecruitFieldDTO recruitFieldDTO = new RecruitFieldDTO(
                    recruitPost.getRecruitPostId(),
                    field
            );
            recruitFieldDTOS.add(recruitFieldDTO);
        }
        return recruitFieldDTOS;
    }

    private List<RecruitField> convertToRecruitFieldEntities(List<RecruitFieldDTO> recruitFieldDTOS, String title) {
        return recruitFieldDTOS.stream()
                .map(dto -> {
                    RecruitPost recruitPost = recruitPostRepository.findRecruitPostByTitle(title);
                    return new RecruitField(recruitPost, dto.getField());
                })
                .collect(Collectors.toList());
    }

    private List<RecruitPersonalityDTO> addRecruitPostPersonalityDTO(RecruitPost recruitPost, RecruitPostRequestDTO recruitPostRequestDTO){
        List<RecruitPersonalityDTO> recruitPersonalityDTOS = new ArrayList<>(); //빈 리스트 생성
        List<String> recruitPersonalities = recruitPostRequestDTO.getRecruitPersonalities();

        for (String personality : recruitPersonalities) {
            RecruitPersonalityDTO recruitPersonalityDTO = new RecruitPersonalityDTO(
                    recruitPost.getRecruitPostId(),
                    personality
            );
            recruitPersonalityDTOS.add(recruitPersonalityDTO);
        }
        return recruitPersonalityDTOS;
    }

    private List<RecruitPersonality> convertToRecruitPersonalityEntities(List<RecruitPersonalityDTO> recruitPersonalityDTOS, String title) {
        return recruitPersonalityDTOS.stream()
                .map(dto -> {
                    RecruitPost recruitPost = recruitPostRepository.findRecruitPostByTitle(title);
                    return new RecruitPersonality(recruitPost, dto.getPersonality());
                })
                .collect(Collectors.toList());
    }

    @Transactional //게시글 삭제
    public String deleteRecruitPost(int recruitPostId, int userId){
        User user = memberRepository.findUserByUserId(userId);
        RecruitPost recruitPost = recruitPostRepository.findRecruitPostByRecruitPostId(recruitPostId);
        Team team = teamRepository.findTeamByRecruitPostId(recruitPost);

        if (recruitPost.getUserId() == user) {
            teamRepository.delete(team);
            recruitPostRepository.deleteById(recruitPostId);
            return "success";
        }
        return "fail";
    }

    @Transactional //게시글 수정
    public void editRecruitPost(int recruitPostId, RecruitPostRequestDTO recruitPostRequestDTO, int userId){
        RecruitPost recruitPost = recruitPostRepository.findRecruitPostByRecruitPostId(recruitPostId);
        if(recruitPostRequestDTO.getTitle()!=null){
            recruitPost.setTitle(recruitPostRequestDTO.getTitle());
        }
        if(recruitPostRequestDTO.getContent()!=null){
            recruitPost.setContent(recruitPostRequestDTO.getContent());
        }
        if(recruitPostRequestDTO.getMajorLectureName()!=null){
            recruitPost.setMajorLectureName(MajorLectureEnum.valueOfLabel(recruitPostRequestDTO.getMajorLectureName()));
        }

        recruitPostRepository.save(recruitPost);
    }

    @Override // 게시글 조회
    @Transactional
    public RecruitPostDetailResponseDTO getRecruitPost(int recruitPostId) {
        RecruitPost recruitPost = recruitPostRepository.findRecruitPostByRecruitPostId(recruitPostId);
        User teamLeader = recruitPost.getUserId();
        UserRank userRank = userRankRepository.findUserRankByUserId(teamLeader);
        DonggaeRank donggaeRank = DonggaeRank.DDONGGAE;
        if (userRank != null){
            donggaeRank = userRank.getRankName();
        }

        List<UserLanguageResponse> userLanguageDTOS = getLanguageResponse(teamLeader);
        List<UserInterestFieldResponse> userInterestFieldDTOS = getInterestFieldResponse(teamLeader);
        List<UserPersonalityResponse> userPersonalityDTOS = getPersonalityResponse(teamLeader);

        List<RecruitLanguageResponse> recruitLanguageResponses = getRecruitLanguageResponse(recruitPost);
        List<RecruitPersonalityResponse> recruitPersonalityResponses = getRecruitPersonalityResponse(recruitPost);
        List<RecruitFieldResponse> recruitFieldResponses = getRecruitFieldResponse(recruitPost);

        String majorLecture = null;
        if (recruitPost.getMajorLectureName() != null)
            majorLecture = recruitPost.getMajorLectureName().label();

        RecruitPostDetailResponseDTO recruitPostDetailResponseDTO = new RecruitPostDetailResponseDTO(
                recruitPost.getRecruitPostId(),
                recruitPost.getTitle(),
                recruitPost.getContent(),
                majorLecture,
                recruitPost.getCreatedDate(),
                recruitFieldResponses,
                recruitLanguageResponses,
                recruitPersonalityResponses,
                recruitPost.getUserId(),
                teamLeader.getGithubName(),
                teamLeader.getDguEmail(),
                teamLeader.getIntro(),
                teamLeader.getBoj_rank().label(),
                donggaeRank.label(),
                userLanguageDTOS,
                userInterestFieldDTOS,
                userPersonalityDTOS
        );

        return recruitPostDetailResponseDTO;
    }
    private List<RecruitLanguageResponse> getRecruitLanguageResponse(RecruitPost recruitPost) {
        List<RecruitLanguage> recruitLanguages = recruitLanguageRepository.findAllByRecruitPostId(recruitPost);
        List<RecruitLanguageResponse> responses = new ArrayList<>(); //빈 리스트 생성

        for (RecruitLanguage recruitLanguage : recruitLanguages) {
            RecruitLanguageResponse recruitLanguageResponse = new RecruitLanguageResponse(
                    recruitLanguage.getLanguage().label()
            );
            responses.add(recruitLanguageResponse);
        }
        return responses;
    }

    private List<RecruitFieldResponse> getRecruitFieldResponse(RecruitPost recruitPost) {
        List<RecruitField> recruitFields = recruitFieldRepository.findAllByRecruitPostId(recruitPost);
        List<RecruitFieldResponse> responses = new ArrayList<>(); //빈 리스트 생성

        for (RecruitField recruitField : recruitFields) {
            RecruitFieldResponse recruitFieldResponse = new RecruitFieldResponse(
                    recruitField.getField().label()
            );
            responses.add(recruitFieldResponse);
        }
        return responses;
    }
    private List<RecruitPersonalityResponse> getRecruitPersonalityResponse(RecruitPost recruitPost) {
        List<RecruitPersonality> recruitPersonalities = recruitPersonalityRepository.findAllByRecruitPostId(recruitPost);
        List<RecruitPersonalityResponse> responses = new ArrayList<>(); //빈 리스트 생성

        for (RecruitPersonality recruitPersonality : recruitPersonalities) {
            RecruitPersonalityResponse recruitPersonalityResponse = new RecruitPersonalityResponse(
                    recruitPersonality.getPersonality().label()
            );
            responses.add(recruitPersonalityResponse);
        }
        return responses;
    }

    private List<UserLanguageResponse> getLanguageResponse(User user) {
        List<UserLanguage> userLanguages = userLanguageRepository.findAllByUserId(user);
        List<UserLanguageResponse> responses = new ArrayList<>(); //빈 리스트 생성

        for (UserLanguage userLanguage : userLanguages) {
            UserLanguageResponse userLanguageResponse = new UserLanguageResponse(
                    userLanguage.getLanguage().label()
            );
            responses.add(userLanguageResponse);
        }
        return responses;
    }

    //user에 해당하는 userInterestField받아오기
    private List<UserInterestFieldResponse> getInterestFieldResponse(User user) {
        List<UserInterestField> userInterestFields = userInterestFieldRepository.findAllByUserId(user);
        List<UserInterestFieldResponse> responses = new ArrayList<>();

        for (UserInterestField userInterestField : userInterestFields) {
            UserInterestFieldResponse userInterestFieldResponse = new UserInterestFieldResponse(
                    userInterestField.getInterestField().label()
            );
            responses.add(userInterestFieldResponse);
        }
        return responses;
    }

    //user에 해당하는 userPersonality받아오기
    private List<UserPersonalityResponse> getPersonalityResponse(User user) {
        List<UserPersonality> userPersonalities = userPersonalityRepository.findAllByUserId(user);
        List<UserPersonalityResponse> responses = new ArrayList<>();

        for (UserPersonality userPersonality : userPersonalities) {
            UserPersonalityResponse userPersonalityResponse = new UserPersonalityResponse(
                    userPersonality.getPersonality().label()
            );
            responses.add(userPersonalityResponse);
        }
        return responses;
    }

    //user에 해당하는 studyField받아오기
    private List<UserStudyFieldResponse> getStudyFieldResponse(User user) {
        List<UserStudyField> userStudyFields = userStudyFieldRepository.findAllByUserId(user);
        List<UserStudyFieldResponse> responses = new ArrayList<>();

        for (UserStudyField userStudyField : userStudyFields) {
            UserStudyFieldResponse userStudyFieldResponse = new UserStudyFieldResponse(
                    userStudyField.getStudyField().label()
            );
            responses.add(userStudyFieldResponse);
        }
        return responses;
    }

    @Transactional
    @Override
    public void completeRecruitPost(TeamIdRequest teamIdRequest) {
        Team team = teamRepository.findTeamByTeamId(teamIdRequest.getTeamId());
        RecruitPost recruitPost = team.getRecruitPostId();
        try {
            validateTeamAndPost(team, recruitPost);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        recruitPost.complete();

        recruitPostRepository.save(recruitPost); // 변경사항 저장

        //팀장의 leader_count & team_exp_count, 팀원의 team_exp_count 증가시키기
        //team에 해당하는 teamMembers
        List<TeamMember> teamMembers = teamMemberRepository.findTeamMembersByTeamId(team);
        for (TeamMember teamMember : teamMembers) {
            User user = teamMember.getUserId();
            int userLeaderCnt = user.getLeaderCount();

            if (teamMember.getIsLeader() == Boolean.TRUE) {
                userLeaderCnt += 1;
            }
            User saveUser = User.builder()
                    .userId(user.getUserId())
                    .boj_rank(user.getBoj_rank())
                    .devTestScore(user.getDevTestScore())
                    .dguEmail(user.getDguEmail())
                    .githubName(user.getGithubName())
                    .intro(user.getIntro())
                    .leaderCount(userLeaderCnt)
                    .teamExpCount(user.getTeamExpCount()+1)
                    .build();
            memberRepository.save(saveUser);
        }
    }

    private void validateTeamAndPost(Team team, RecruitPost recruitPost) throws Exception{
        if (team == null) {
            throw new Exception("team is null");
        }
        if (recruitPost == null) {
            throw new Exception("recruitPost is null");
        }
        if (recruitPost.getIsComplete() == Boolean.TRUE) {
            throw new Exception("recruitPost is already closed");
        }
    }

}


