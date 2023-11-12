//package Otwos.Donggae.domain.application.service;
//
//import Otwos.Donggae.DAO.Application;
//import Otwos.Donggae.DAO.Recruit.RecruitPost;
//import Otwos.Donggae.DAO.User.User;
//import Otwos.Donggae.DAO.User.UserInterestField;
//import Otwos.Donggae.DAO.User.UserLanguage;
//import Otwos.Donggae.DAO.User.UserPersonality;
//import Otwos.Donggae.DAO.User.UserRank;
//import Otwos.Donggae.DAO.User.UserStudyField;
//import Otwos.Donggae.DTO.application.ApplyDTO;
//import Otwos.Donggae.DTO.application.ApplyPage.ApplyPageRequest;
//import Otwos.Donggae.DTO.application.read.ReadApplicationRequest;
//import Otwos.Donggae.DTO.application.read.ReadApplicationResponse;
//import Otwos.Donggae.DTO.member.previewInfo.PreviewUserInfoDTO;
//import Otwos.Donggae.DTO.member.userinfo.UserInterestFieldDTO;
//import Otwos.Donggae.DTO.member.userinfo.UserLanguageDTO;
//import Otwos.Donggae.DTO.member.userinfo.UserPersonalityDTO;
//import Otwos.Donggae.DTO.member.userinfo.UserStudyFieldDTO;
//import Otwos.Donggae.Global.Rank.DonggaeRank;
//import Otwos.Donggae.domain.application.repository.ApplicationRepository;
//import Otwos.Donggae.domain.member.repository.MemberRepository;
//import Otwos.Donggae.domain.member.repository.info.UserInterestFieldRepository;
//import Otwos.Donggae.domain.member.repository.info.UserLanguageRepository;
//import Otwos.Donggae.domain.member.repository.info.UserPersonalityRepository;
//import Otwos.Donggae.domain.member.repository.info.UserStudyFieldRepository;
//import Otwos.Donggae.domain.rank.repository.UserRankRepository;
//import java.util.ArrayList;
//import java.util.List;
//import org.springframework.stereotype.Service;
//
//@Service
//public class ApplicationServiceImpl implements ApplicationService{
//    private ApplicationRepository applicationRepository;
//    private MemberRepository memberRepository;
//    private RecruitPostRepository recruitPostRepository;
//    private UserInterestFieldRepository userInterestFieldRepository;
//    private UserLanguageRepository userLanguageRepository;
//    private UserPersonalityRepository userPersonalityRepository;
//    private UserStudyFieldRepository userStudyFieldRepository;
//    private UserRankRepository userRankRepository;
//
//    public ApplicationServiceImpl(ApplicationRepository applicationRepository, MemberRepository memberRepository,
//                                  RecruitPostRepository recruitPostRepository,
//                                  UserInterestFieldRepository interestFieldRepository,
//                                  UserLanguageRepository languageRepository,
//                                  UserPersonalityRepository personalityRepository,
//                                  UserStudyFieldRepository studyFieldRepository,
//                                  UserRankRepository userRankRepository) {
//        this.applicationRepository = applicationRepository;
//        this.memberRepository = memberRepository;
//        this.recruitPostRepository = recruitPostRepository;
//        this.userInterestFieldRepository = interestFieldRepository;
//        this.userLanguageRepository = languageRepository;
//        this.userPersonalityRepository = personalityRepository;
//        this.userStudyFieldRepository = studyFieldRepository;
//        this.userRankRepository = userRankRepository;
//    }
//
//    //지원하는 글 쓰고 지원하기 버튼 클릭 시
//    @Override
//    public void applyFor(ApplyDTO applyDTO) {
//        User user = memberRepository.findUserByUserId(applyDTO.getUserId());
//        RecruitPost recruitPost = recruitPostRepository.findRecruitPostByRecruitPostId(applyDTO.getRecruitPostId());
//        //예외처리 하고 저장
//        try {
//            validateUserAndRecruitPost(user, recruitPost);
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        }
//
//        //해당 user와 recruitPost가 있으면 저장
//        //applicationId따로 안넣어도 알아서 들어가겠지?
//        applicationRepository.save(applyDTO.toEntity(user, recruitPost));
//    }
//
//    private void validateUserAndRecruitPost(User user, RecruitPost recruitPost) throws Exception {
//        if (user == null) {
//            throw new Exception("해당 user가 없습니다.");
//        }
//        if (recruitPost == null) {
//            throw new Exception("해당 recruitPost가 없습니다.");
//        }
//    }
//
//
//    //지원서 조회
//    @Override
//    public ReadApplicationResponse readApplication(ReadApplicationRequest applicationRequest) {
//        User user = memberRepository.findUserByUserId(applicationRequest.getUserId());
//        RecruitPost recruitPost = recruitPostRepository.findRecruitPostByRecruitPostId(applicationRequest.getRecruitPostId());
//        //예외처리
//        try {
//            validateUserAndRecruitPost(user, recruitPost);
//            validateApplication(user, recruitPost);
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        }
//
//        List<UserLanguageDTO> userLanguageDTOS = getLanguageDTO(user);
//        List<UserInterestFieldDTO> userInterestFieldDTOS = getInterestFieldDTO(user);
//        List<UserPersonalityDTO> userPersonalityDTOS = getPersonalityDTO(user);
//        List<UserStudyFieldDTO> userStudyFieldDTOS = getStudyFieldDTO(user);
//
//        //rank 엔티티에 없으면 그냥 "똥개" 보냄
//        UserRank userRank = userRankRepository.findUserRankByUserId(user);
//        DonggaeRank donggaeRank = DonggaeRank.똥개;
//        if (userRank != null){
//            donggaeRank = userRank.getRankName();
//        }
//
//        //해당 user와 recruitPost가 있으면 보여주기
//        PreviewUserInfoDTO previewUserInfoDTO = new PreviewUserInfoDTO(
//                user.getGithubName(),
//                user.getIntro(),
//                user.getBoj_rank(),
//                user.getDguEmail(),
//                donggaeRank,
//                userLanguageDTOS,
//                userInterestFieldDTOS,
//                userPersonalityDTOS,
//                userStudyFieldDTOS
//        );
//
//        Application application = applicationRepository.findApplicationByUserIdAndRecruitPostId(user, recruitPost);
//
//        ReadApplicationResponse readApplicationResponse = new ReadApplicationResponse(
//                previewUserInfoDTO,
//                application.getSelfIntro(),
//                application.getContent()
//        );
//
//        return readApplicationResponse;
//    }
//
//    //지원하기 페이지 갔을 때 내정보 표시
//    @Override
//    public PreviewUserInfoDTO applyPageInfo(ApplyPageRequest applyPageRequest) {
//        //구현
//    }
//
//    //userId와 recruitPost에 해당하는 지원서 있는지 확인
//    private void validateApplication(User user, RecruitPost recruitPost) throws Exception{
//        Application application = applicationRepository.findApplicationByUserIdAndRecruitPostId(
//                user, recruitPost);
//        if (application == null) {
//            throw new Exception("해당 지원서가 없습니다.");
//        }
//    }
//
//    //user에 해당하는 userLanguage받아오기
//    private List<UserLanguageDTO> getLanguageDTO(User user) {
//        List<UserLanguage> userLanguages = userLanguageRepository.findUserLanguagesByUserId(user);
//        List<UserLanguageDTO> userLanguageDTOS = new ArrayList<>(); //빈 리스트 생성
//
//        for (UserLanguage userLanguage : userLanguages) {
//            UserLanguageDTO userLanguageDTO = new UserLanguageDTO(
//                    user.getUserId(),
//                    userLanguage.getLanguage()
//            );
//            userLanguageDTOS.add(userLanguageDTO);
//        }
//        return userLanguageDTOS;
//    }
//
//    //user에 해당하는 userInterestField받아오기
//    private List<UserInterestFieldDTO> getInterestFieldDTO(User user) {
//        List<UserInterestField> userInterestFields = userInterestFieldRepository.findUserInterestFieldsByUserId(user);
//        List<UserInterestFieldDTO> userInterestFieldDTOS = new ArrayList<>();
//
//        for (UserInterestField userInterestField : userInterestFields) {
//            UserInterestFieldDTO userInterestFieldDTO = new UserInterestFieldDTO(
//                    user.getUserId(),
//                    userInterestField.getInterestField()
//            );
//            userInterestFieldDTOS.add(userInterestFieldDTO);
//        }
//        return userInterestFieldDTOS;
//    }
//
//    //user에 해당하는 userPersonality받아오기
//    private List<UserPersonalityDTO> getPersonalityDTO(User user) {
//        List<UserPersonality> userPersonalities = userPersonalityRepository.findUserPersonalitiesByUserId(user);
//        List<UserPersonalityDTO> userPersonalityDTOS = new ArrayList<>();
//
//        for (UserPersonality userPersonality : userPersonalities) {
//            UserPersonalityDTO userPersonalityDTO = new UserPersonalityDTO(
//                    user.getUserId(),
//                    userPersonality.getPersonality()
//            );
//            userPersonalityDTOS.add(userPersonalityDTO);
//        }
//        return userPersonalityDTOS;
//    }
//
//    //user에 해당하는 studyField받아오기
//    private List<UserStudyFieldDTO> getStudyFieldDTO(User user) {
//        List<UserStudyField> userStudyFields = userStudyFieldRepository.findUserStudyFieldsByUserId(user);
//        List<UserStudyFieldDTO> userStudyFieldDTOS = new ArrayList<>();
//
//        for (UserStudyField userStudyField : userStudyFields) {
//            UserStudyFieldDTO userStudyFieldDTO = new UserStudyFieldDTO(
//                    user.getUserId(),
//                    userStudyField.getStudyField()
//            );
//            userStudyFieldDTOS.add(userStudyFieldDTO);
//        }
//        return userStudyFieldDTOS;
//    }
//}
