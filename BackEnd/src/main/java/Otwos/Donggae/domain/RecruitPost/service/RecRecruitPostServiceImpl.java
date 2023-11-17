package Otwos.Donggae.domain.RecruitPost.service;

import Otwos.Donggae.DAO.Recruit.RecruitLanguage;
import Otwos.Donggae.DAO.Recruit.RecruitPost;
import Otwos.Donggae.DAO.User.*;
import Otwos.Donggae.DTO.RecruitPost.RecRecruitPostDTO;
import Otwos.Donggae.Global.MajorLectureEnum;
import Otwos.Donggae.Global.Rank.BaekjoonRank;
import Otwos.Donggae.Global.Rank.DonggaeRank;
import Otwos.Donggae.domain.RecruitPost.Repository.RecruitPostRepository;
import Otwos.Donggae.domain.member.repository.MemberRepository;
import Otwos.Donggae.domain.member.repository.info.UserInterestFieldRepository;
import Otwos.Donggae.domain.member.repository.info.UserLanguageRepository;
import Otwos.Donggae.domain.member.repository.info.UserPersonalityRepository;
import Otwos.Donggae.domain.rank.repository.UserRankRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class RecRecruitPostServiceImpl implements RecRecruitPostService {

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
    private UserRankRepository userRankRepository;

    @Override
    public List<RecRecruitPostDTO> recommendRecruitPost(int userId) {
        // user 가져오기
        User user = memberRepository.findUserWithDetailsByUserId(userId);

        // 1) isComplete==false인 게시물 && 자신(user)이 작성한 게시물 제외
        List<RecruitPost> recommendedPostList = recruitPostRepository.findAllByIsCompleteAndUserIdNot(false, user);

        // 각 게시물에 대한 우선순위 부여
        HashMap<RecruitPost, Integer> priorityMap = calculatePriorities(user, recommendedPostList);

        // priorityMap을 값으로 정렬하여 상위 4개 선택 (우선순위 높은 4개)
        List<RecruitPost> topPosts = selectTopPriorityPosts(priorityMap, 4);

        // 전체 게시물이 4개가 안될 때 - 처리 해야 하나?? or 추천 게시물 수도 response로?

        // DTO 생성 및 반환
        return createRecruitPostDTOList(topPosts);
    }

    private HashMap<RecruitPost, Integer> calculatePriorities(User user, List<RecruitPost> recommendedPostList) {
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

        // RecruitPost의 우선순위를 저장하는 map
        HashMap<RecruitPost, Integer> priorityMap = new HashMap<RecruitPost, Integer>();
        recommendedPostList.forEach(post -> {
            int priority = 0;
            // 여기서 언어, 관심분야, 성격 일치하는지 확인하여 우선순위 계산
            priority += calculatePriorityForMatchingAttributes(post, userLanguageList, userInterestFieldList, userPersonalityList);
            priorityMap.put(post, priority);
        });
        return priorityMap;
    }

    private int calculatePriorityForMatchingAttributes(RecruitPost post, Set<String> userLanguageList, Set<String> userInterestFieldList, Set<String> userPersonalityList) {
        int priority = 0;
        if (post.getRecruitLanguages().stream().anyMatch(lang -> userLanguageList.contains(lang.getLanguage().name()))) {
            priority++;
        }
        if (post.getRecruitFields().stream().anyMatch(field -> userInterestFieldList.contains(field.getField().name()))) {
            priority++;
        }
        if (post.getRecruitPersonalities().stream().anyMatch(pers -> userPersonalityList.contains(pers.getPersonality().name()))) {
            priority++;
        }
        return priority;
    }

    private List<RecRecruitPostDTO> createRecruitPostDTOList(List<RecruitPost> topPosts) {
        // response 생성
        List<RecRecruitPostDTO> recommendPostsResponse = new ArrayList<>();

        for (RecruitPost topPost : topPosts) {
            // 모집글 정보
            int postId = topPost.getRecruitPostId();
            MajorLectureEnum majorLectureName = topPost.getMajorLectureName();
            String title = topPost.getTitle();
            List<String> recruitLanguages = topPost.getRecruitLanguages()
                    .stream()
                    .map(recruitLanguage -> recruitLanguage.getLanguage().name()).toList();
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
            String createdDate = simpleDateFormat.format(topPost.getCreatedDate());

            // 모집글 작성자(팀장) 정보 - 쿼리 4개
            User teamLeader = topPost.getUserId();
            DonggaeRank donggaeRank = userRankRepository.findUserRankByUserId(teamLeader).getRankName();
            BaekjoonRank bojRank = teamLeader.getBoj_rank();
            String userName = teamLeader.getGithubName();

            RecRecruitPostDTO recRecruitPostDTO = new RecRecruitPostDTO(postId, majorLectureName, title, recruitLanguages, donggaeRank, bojRank, userName, createdDate);
            recommendPostsResponse.add(recRecruitPostDTO);
        }

        return recommendPostsResponse;
    }

    private static List<RecruitPost> selectTopPriorityPosts(Map<RecruitPost, Integer> priorityMap, int limitSize) {
        List<RecruitPost> topPriorityPosts  = priorityMap.entrySet()
                .stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .limit(limitSize*2) // 추천할 개수의 두 배를 추출
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());

        // 추출된 게시물들을 섞는다.
        Collections.shuffle(topPriorityPosts);

        // 섞은 후 상위 N개만 선택
        return topPriorityPosts.stream().limit(limitSize).collect(Collectors.toList());
    }
}
