package Otwos.Donggae.domain.member.service;

import Otwos.Donggae.DAO.User.*;
import Otwos.Donggae.DTO.member.myPage.MyPageRequestDTO;
import Otwos.Donggae.DTO.member.myPage.MyPageResponseDTO;
import Otwos.Donggae.DTO.member.userinfo.UserInterestFieldDTO;
import Otwos.Donggae.DTO.member.userinfo.UserLanguageDTO;
import Otwos.Donggae.DTO.member.userinfo.UserPersonalityDTO;
import Otwos.Donggae.DTO.member.userinfo.response.UserInterestFieldResponse;
import Otwos.Donggae.DTO.member.userinfo.response.UserLanguageResponse;
import Otwos.Donggae.DTO.member.userinfo.response.UserPersonalityResponse;
import Otwos.Donggae.DTO.member.userinfo.response.UserStudyFieldResponse;
import Otwos.Donggae.Global.FieldEnum;
import Otwos.Donggae.Global.LanguageEnum;
import Otwos.Donggae.Global.PersonalityEnum;
import Otwos.Donggae.Global.Rank.DonggaeRank;
import Otwos.Donggae.domain.member.repository.MemberRepository;
import Otwos.Donggae.domain.member.repository.info.UserInterestFieldRepository;
import Otwos.Donggae.domain.member.repository.info.UserLanguageRepository;
import Otwos.Donggae.domain.member.repository.info.UserPersonalityRepository;
import Otwos.Donggae.domain.member.repository.info.UserStudyFieldRepository;
import Otwos.Donggae.domain.rank.repository.UserRankRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class MyPageServiceImpl implements MyPageService{

    @Autowired
    MemberRepository memberRepository;

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


    @Override
    public MyPageResponseDTO showMyInfo(int userId) {
        User user = memberRepository.findUserByUserId(userId);
        List<UserLanguageResponse> userLanguageDTOS = getLanguageResponse(user);
        List<UserInterestFieldResponse> userInterestFieldDTOS = getInterestFieldResponse(user);
        List<UserPersonalityResponse> userPersonalityDTOS = getPersonalityResponse(user);
        List<UserStudyFieldResponse> userStudyFieldDTOS = getStudyFieldResponse(user);
        int teamExpCount = user.getTeamExpCount();
        int leaderCount = user.getLeaderCount();
        int devTestScore = user.getDevTestScore();

        //rank 엔티티에 없으면 그냥 "똥개" 보냄
        UserRank userRank = userRankRepository.findUserRankByUserId(user);
        DonggaeRank donggaeRank = DonggaeRank.DDONGGAE;
        if (userRank != null){
            donggaeRank = userRank.getRankName();
        }
        MyPageResponseDTO myPageResponseDTO = new MyPageResponseDTO(
                user.getGithubName(),
                user.getIntro(),
                user.getBoj_rank().label(),
                user.getDguEmail(),
                donggaeRank.label(),
                teamExpCount,
                leaderCount,
                devTestScore,
                userLanguageDTOS,
                userInterestFieldDTOS,
                userPersonalityDTOS,
                userStudyFieldDTOS
        );
        return myPageResponseDTO;
    }

    @Override
    @Transactional
    public void editMyInfo(MyPageRequestDTO myPageRequestDTO, int userId) {
        User user = memberRepository.findUserByUserId(userId);

        if(myPageRequestDTO.getSelfIntro()!=null){
            user.setIntro(myPageRequestDTO.getSelfIntro());
        }

        if(myPageRequestDTO.getUserLanguages()!=null){

            List<UserLanguageDTO> userLanguageDTOS = addUserLanguageDTO(user, myPageRequestDTO);

            List<UserLanguage> userLanguageList = convertToUserLanguageEntities(userLanguageDTOS,user);

            userLanguageRepository.deleteAllByUserId(user);

            userLanguageRepository.saveAll(userLanguageList);

        }

        if(myPageRequestDTO.getUserPersonalities()!=null){

            List<UserPersonalityDTO> userPersonalityDTOS = addUserPersonalityDTO(user, myPageRequestDTO);

            List<UserPersonality> userPersonalityList = convertToUserPersonalityEntities(userPersonalityDTOS, user);

            userPersonalityRepository.deleteAllByUserId(user);

            userPersonalityRepository.saveAll(userPersonalityList);
        }

        if(myPageRequestDTO.getUserInterestFields()!=null){

            List<UserInterestFieldDTO> userInterestFieldDTOS = addUserInterestFieldDTO(user, myPageRequestDTO);

            List<UserInterestField> userInterestFieldList = convertToUserInterestEntities(userInterestFieldDTOS, user);

            userInterestFieldRepository.deleteAllByUserId(user);

            userInterestFieldRepository.saveAll(userInterestFieldList);
        }


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

    private List<UserLanguageDTO> addUserLanguageDTO(User user, MyPageRequestDTO myPageRequestDTO){
        List<UserLanguageDTO> userLanguageDTOS = new ArrayList<>(); //빈 리스트 생성
        List<String> userLanguages = myPageRequestDTO.getUserLanguages();

        for (String language : userLanguages) {
            UserLanguageDTO userLanguageDTO = new UserLanguageDTO(
                    user.getUserId(),
                    language
            );
            userLanguageDTOS.add(userLanguageDTO);
        }
        return userLanguageDTOS;
    }

    private List<UserLanguage> convertToUserLanguageEntities(List<UserLanguageDTO> userLanguageDTOS, User user) {
        return userLanguageDTOS.stream()
                .map(dto -> {
                    return new UserLanguage(user, LanguageEnum.valueOfLabel(dto.getLanguage()));
                })
                .collect(Collectors.toList());
    }

    private List<UserPersonalityDTO> addUserPersonalityDTO(User user, MyPageRequestDTO myPageRequestDTO){
        List<UserPersonalityDTO> userPersonalityDTOS = new ArrayList<>(); //빈 리스트 생성
        List<String> userPersonality = myPageRequestDTO.getUserPersonalities();

        for (String personality : userPersonality) {
            UserPersonalityDTO userPersonalityDTO = new UserPersonalityDTO(
                    user.getUserId(),
                    personality
            );
            userPersonalityDTOS.add(userPersonalityDTO);
        }
        return userPersonalityDTOS;
    }

    private List<UserPersonality> convertToUserPersonalityEntities(List<UserPersonalityDTO> userPersonalityDTOS, User user) {
        return userPersonalityDTOS.stream()
                .map(dto -> {
                    return new UserPersonality(user, PersonalityEnum.valueOfLabel(dto.getPersonality()));
                })
                .collect(Collectors.toList());
    }

    private List<UserInterestFieldDTO> addUserInterestFieldDTO(User user, MyPageRequestDTO myPageRequestDTO){
        List<UserInterestFieldDTO> userInterestFieldDTOS = new ArrayList<>(); //빈 리스트 생성
        List<String> userFields = myPageRequestDTO.getUserInterestFields();

        for (String fields : userFields) {
            // 문자열을 Enum으로 변환
            FieldEnum fieldEnum = FieldEnum.valueOf(fields);

            UserInterestFieldDTO userInterestFieldDTO = new UserInterestFieldDTO(
                    user.getUserId(),
                    fieldEnum.label()
            );
            userInterestFieldDTOS.add(userInterestFieldDTO);
        }
        return userInterestFieldDTOS;
    }

    private List<UserInterestField> convertToUserInterestEntities(List<UserInterestFieldDTO> userInterestFieldDTOS, User user) {
        return userInterestFieldDTOS.stream()
                .map(dto -> {
                    return new UserInterestField(user, FieldEnum.valueOfLabel(dto.getInterestField()));
                })
                .collect(Collectors.toList());
    }

}