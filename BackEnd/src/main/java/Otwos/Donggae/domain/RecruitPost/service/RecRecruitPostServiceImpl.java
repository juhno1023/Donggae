package Otwos.Donggae.domain.RecruitPost.service;

import Otwos.Donggae.DAO.Recruit.RecruitPost;
import Otwos.Donggae.DAO.User.User;
import Otwos.Donggae.DAO.User.UserInterestField;
import Otwos.Donggae.DAO.User.UserLanguage;
import Otwos.Donggae.DAO.User.UserPersonality;
import Otwos.Donggae.Global.LanguageEnum;
import Otwos.Donggae.domain.RecruitPost.Repository.RecruitPostRepository;
import Otwos.Donggae.domain.RecruitPost.Repository.info.RecruitFieldRepository;
import Otwos.Donggae.domain.RecruitPost.Repository.info.RecruitLanguageRepository;
import Otwos.Donggae.domain.RecruitPost.Repository.info.RecruitPersonalityRepository;
import Otwos.Donggae.domain.member.repository.MemberRepository;
import Otwos.Donggae.domain.member.repository.info.UserInterestFieldRepository;
import Otwos.Donggae.domain.member.repository.info.UserLanguageRepository;
import Otwos.Donggae.domain.member.repository.info.UserPersonalityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class RecRecruitPostServiceImpl implements RecRecruitPostService{

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private UserLanguageRepository userLanguageRepository;

    @Autowired
    private UserInterestFieldRepository userInterestFieldRepository;

    @Autowired
    private UserPersonalityRepository userPersonalityRepository;

    @Autowired
    private RecruitPostRepository recruitPostRepository;

    @Autowired
    private RecruitLanguageRepository recruitLanguageRepository;

    @Autowired
    private RecruitPersonalityRepository recruitPersonalityRepository;

    @Autowired
    private RecruitFieldRepository recruitFieldRepository;


    @Override
    public List<UserInterestField> recommendRecruitPost(int userId) {
        // user 정보 가져오기 - 관심분야, 언어, 성격
        User user = memberRepository.findUserByUserId(userId);
        List<UserInterestField> userInterestFieldList = userInterestFieldRepository.findAllByUserId(user);
        List<UserLanguage> userLanguageList = userLanguageRepository.findAllByUserId(user);
        List<UserPersonality> userPersonalityList = userPersonalityRepository.findAllByUserId(user);

        // 1) isComplete==false인 게시물 && 자신(user)이 작성한 게시물 제외
        List<RecruitPost> recommendedPostList = recruitPostRepository.findAllByIsCompleteAndUserId(false, user);

        // 2) 언어 일치하는 recruit post 우선순위 부여

//        List<RecruitPost> sortedRecommendedPostList = recommendedPostList.stream()
//                .sorted((post1, post2) -> {
//                    boolean post1LanguageMatch = userLanguageList.stream()
//                            .anyMatch(userLanguage -> post1.getRecruitLanguages().contains(userLanguage.getLanguage())); // user의 첫 language를 포함하는 게시물
//                    boolean post2LanguageMatch = userLanguageList.stream()
//                            .anyMatch(userLanguage -> post2.getRecruitLanguages().contains(userLanguage.getLanguage()));
//                    return Boolean.compare(post2LanguageMatch, post1LanguageMatch); // true (언어 일치)가 앞으로 오도록
//                })
//                .collect(Collectors.toList());
        
        // 관심분야, 성격 우선순위


        // 전체 게시물이 4개가 안될 때


        return new ArrayList<>();
    }
}
