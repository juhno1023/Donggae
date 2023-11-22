package Otwos.Donggae.domain.member.service;

import Otwos.Donggae.DAO.Recruit.RecruitPost;
import Otwos.Donggae.DAO.User.*;
import Otwos.Donggae.DTO.member.myPage.MyPageRequestDTO;
import Otwos.Donggae.DTO.member.myPage.MyPageResponseDTO;
import Otwos.Donggae.DTO.member.previewInfo.PreviewUserInfoDTO;
import Otwos.Donggae.DTO.member.userinfo.response.UserInterestFieldResponse;
import Otwos.Donggae.DTO.member.userinfo.response.UserLanguageResponse;
import Otwos.Donggae.DTO.member.userinfo.response.UserPersonalityResponse;
import Otwos.Donggae.DTO.member.userinfo.response.UserStudyFieldResponse;
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
        DonggaeRank donggaeRank = DonggaeRank.똥개;
        if (userRank != null){
            donggaeRank = userRank.getRankName();
        }
        MyPageResponseDTO myPageResponseDTO = new MyPageResponseDTO(
                user.getGithubName(),
                user.getIntro(),
                user.getBoj_rank(),
                user.getDguEmail(),
                donggaeRank,
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

        if(myPageRequestDTO.getUserLanguageDTOS()!=null){

            List<UserLanguage> userLanguageList = myPageRequestDTO.getUserLanguageDTOS();

            Set<UserLanguage> aSet = new HashSet<UserLanguage>();
            for (UserLanguage x : userLanguageList)
                aSet.add(x);
            user.setUserLanguages(aSet);
        }

        if(myPageRequestDTO.getUserPersonalityDTOS()!=null){

            List<UserPersonality> userPersonalityList = myPageRequestDTO.getUserPersonalityDTOS();

            Set<UserPersonality> bSet = new HashSet<UserPersonality>();
            for (UserPersonality x : userPersonalityList)
                bSet.add(x);
            user.setUserPersonalities(bSet);
        }

        if(myPageRequestDTO.getUserInterestFieldDTOS()!=null){

            List<UserInterestField> userInterestFieldList = myPageRequestDTO.getUserInterestFieldDTOS();

            Set<UserInterestField> cSet = new HashSet<UserInterestField>();
            for (UserInterestField x : userInterestFieldList)
                cSet.add(x);
            user.setUserInterestFields(cSet);
        }

        if(myPageRequestDTO.getUserStudyFieldDTOS()!=null){

            List<UserStudyField> userStudyFieldList = myPageRequestDTO.getUserStudyFieldDTOS();

            Set<UserStudyField> dSet = new HashSet<UserStudyField>();
            for (UserStudyField x : userStudyFieldList)
                dSet.add(x);
            user.setUserStudyFields(dSet);
        }

        memberRepository.save(user);
    }

    private List<UserLanguageResponse> getLanguageResponse(User user) {
        List<UserLanguage> userLanguages = userLanguageRepository.findAllByUserId(user);
        List<UserLanguageResponse> responses = new ArrayList<>(); //빈 리스트 생성

        for (UserLanguage userLanguage : userLanguages) {
            UserLanguageResponse userLanguageResponse = new UserLanguageResponse(
                    userLanguage.getLanguage()
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
                    userInterestField.getInterestField()
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
                    userPersonality.getPersonality()
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
                    userStudyField.getStudyField()
            );
            responses.add(userStudyFieldResponse);
        }
        return responses;
    }


}
