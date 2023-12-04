package Otwos.Donggae.domain.application.service;

import static java.sql.Types.NULL;

import Otwos.Donggae.DAO.Application;
import Otwos.Donggae.DAO.Recruit.RecruitPost;
import Otwos.Donggae.DAO.Team.Team;
import Otwos.Donggae.DAO.User.User;
import Otwos.Donggae.DAO.User.UserInterestField;
import Otwos.Donggae.DAO.User.UserLanguage;
import Otwos.Donggae.DAO.User.UserPersonality;
import Otwos.Donggae.DAO.User.UserRank;
import Otwos.Donggae.DAO.User.UserStudyField;
import Otwos.Donggae.DTO.application.ApplyDTO;
import Otwos.Donggae.DTO.application.ApplyTeamRequest;
import Otwos.Donggae.DTO.application.read.ReadApplicationRequest;
import Otwos.Donggae.DTO.application.read.ReadApplicationResponse;
import Otwos.Donggae.DTO.member.previewInfo.PreviewUserInfoDTO;
import Otwos.Donggae.DTO.member.userinfo.response.UserInterestFieldResponse;
import Otwos.Donggae.DTO.member.userinfo.response.UserLanguageResponse;
import Otwos.Donggae.DTO.member.userinfo.response.UserPersonalityResponse;
import Otwos.Donggae.DTO.member.userinfo.response.UserStudyFieldResponse;
import Otwos.Donggae.Global.Rank.DonggaeRank;
import Otwos.Donggae.domain.RecruitPost.Repository.RecruitPostRepository;
import Otwos.Donggae.domain.application.repository.ApplicationRepository;
import Otwos.Donggae.domain.member.repository.MemberRepository;
import Otwos.Donggae.domain.member.repository.info.UserInterestFieldRepository;
import Otwos.Donggae.domain.member.repository.info.UserLanguageRepository;
import Otwos.Donggae.domain.member.repository.info.UserPersonalityRepository;
import Otwos.Donggae.domain.member.repository.info.UserStudyFieldRepository;
import Otwos.Donggae.domain.rank.repository.UserRankRepository;
import Otwos.Donggae.domain.team.repository.TeamRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ApplicationServiceImpl implements ApplicationService{
    @Autowired
    private ApplicationRepository applicationRepository;
    @Autowired
    private MemberRepository memberRepository;
    @Autowired
    private RecruitPostRepository recruitPostRepository;
    @Autowired
    private UserInterestFieldRepository userInterestFieldRepository;
    @Autowired
    private UserLanguageRepository userLanguageRepository;
    @Autowired
    private UserPersonalityRepository userPersonalityRepository;
    @Autowired
    private UserStudyFieldRepository userStudyFieldRepository;
    @Autowired
    private UserRankRepository userRankRepository;
    @Autowired
    private TeamRepository teamRepository;

    //지원하는 글 쓰고 지원하기 버튼 클릭 시
    @Override
    public void applyFor(int userId, ApplyTeamRequest request) {
        User user = memberRepository.findUserByUserId(userId);
        RecruitPost recruitPost = recruitPostRepository.findRecruitPostByRecruitPostId(request.getRecruitPostId());
        //예외처리 하고 저장
        try {
            validateUserAndRecruitPost(user, recruitPost);
            //이미 해당 글에 지원했으면 에러
            validDuplicateApplication(user, recruitPost);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        //해당 user와 recruitPost가 있으면 저장
        ApplyDTO applyDTO = new ApplyDTO(
                NULL,
                request.getSelfIntro(),
                request.getContent(),
                NULL
        );
        applicationRepository.save(applyDTO.toEntity(user, recruitPost));
    }

    private void validateUserAndRecruitPost(User user, RecruitPost recruitPost) throws Exception {
        if (user == null) {
            throw new Exception("해당 user가 없습니다.");
        }
        if (recruitPost == null) {
            throw new Exception("해당 recruitPost가 없습니다.");
        }
    }

    //application이 이미 있는지 확인
    private void validDuplicateApplication(User user, RecruitPost recruitPost) throws Exception {
        Application application = applicationRepository.findApplicationByUserIdAndRecruitPostId(user, recruitPost);
        if (application != null) {
            throw new Exception("application already exist");
        }
    }


    //지원서 조회
    @Override
    public ReadApplicationResponse readApplication(ReadApplicationRequest applicationRequest) {
        User user = memberRepository.findUserByUserId(applicationRequest.getUserId());
        Team team = teamRepository.findTeamByTeamId(applicationRequest.getTeamId());
        RecruitPost recruitPost = team.getRecruitPostId();

        //예외처리
        try {
            validateUserAndRecruitPost(user, recruitPost);
            validateApplication(user, recruitPost);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        //해당 user와 recruitPost가 있으면 보여주기
        PreviewUserInfoDTO previewUserInfoDTO = createPreviewUserInfoDTO(user);

        Application application = applicationRepository.findApplicationByUserIdAndRecruitPostId(user, recruitPost);

        return new ReadApplicationResponse(
                previewUserInfoDTO,
                application.getSelfIntro(),
                application.getContent()
        );
    }

    //지원하기 페이지 갔을 때 내정보 표시
    @Override
    public PreviewUserInfoDTO applyPageInfo(int userId) {
        User user = memberRepository.findUserByUserId(userId);
        return createPreviewUserInfoDTO(user);
    }

    private PreviewUserInfoDTO createPreviewUserInfoDTO(User user) {
        List<UserLanguageResponse> userLanguageDTOS = getLanguageResponse(user);
        List<UserInterestFieldResponse> userInterestFieldDTOS = getInterestFieldResponse(user);
        List<UserPersonalityResponse> userPersonalityDTOS = getPersonalityResponse(user);
        List<UserStudyFieldResponse> userStudyFieldDTOS = getStudyFieldResponse(user);

        DonggaeRank donggaeRank = Optional.ofNullable(userRankRepository.findUserRankByUserId(user))
                .map(UserRank::getRankName)
                .orElse(DonggaeRank.DDONGGAE);

        return new PreviewUserInfoDTO(
                user.getGithubName(),
                user.getIntro(),
                user.getBoj_rank().label(),
                user.getDguEmail(),
                donggaeRank.label(),
                userLanguageDTOS,
                userInterestFieldDTOS,
                userPersonalityDTOS,
                userStudyFieldDTOS
        );
    }


    //userId와 recruitPost에 해당하는 지원서 있는지 확인
    private void validateApplication(User user, RecruitPost recruitPost) throws Exception{
        Application application = applicationRepository.findApplicationByUserIdAndRecruitPostId(
                user, recruitPost);
        if (application == null) {
            throw new Exception("해당 지원서가 없습니다.");
        }
    }

    //user에 해당하는 userLanguage받아오기
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
}
