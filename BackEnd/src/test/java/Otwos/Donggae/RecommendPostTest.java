package Otwos.Donggae;

import Otwos.Donggae.DAO.Recruit.RecruitField;
import Otwos.Donggae.DAO.Recruit.RecruitLanguage;
import Otwos.Donggae.DAO.Recruit.RecruitPersonality;
import Otwos.Donggae.DAO.Recruit.RecruitPost;
import Otwos.Donggae.DAO.User.User;
import Otwos.Donggae.DAO.User.UserInterestField;
import Otwos.Donggae.DAO.User.UserLanguage;
import Otwos.Donggae.DAO.User.UserPersonality;
import Otwos.Donggae.DTO.RecruitPost.RecRecruitPostDTO;
import Otwos.Donggae.Global.MajorLectureEnum;
import Otwos.Donggae.Global.Rank.BaekjoonRank;
import Otwos.Donggae.Global.Rank.DonggaeRank;
import Otwos.Donggae.domain.RecruitPost.Repository.RecruitPostRepository;
import Otwos.Donggae.domain.RecruitPost.service.RecRecruitPostService;
import Otwos.Donggae.domain.RecruitPost.service.RecRecruitPostServiceImpl;
import Otwos.Donggae.domain.RecruitPost.service.RecruitPostService;
import Otwos.Donggae.domain.RecruitPost.service.RecruitPostServiceImpl;
import Otwos.Donggae.domain.member.repository.MemberRepository;
import Otwos.Donggae.domain.member.repository.info.UserInterestFieldRepository;
import Otwos.Donggae.domain.member.repository.info.UserLanguageRepository;
import Otwos.Donggae.domain.member.repository.info.UserPersonalityRepository;
import Otwos.Donggae.domain.rank.repository.UserRankRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

@SpringBootTest
@Transactional
public class RecommendPostTest {

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

    @Autowired
    private RecRecruitPostService recRecruitPostService;

    @Test
    void calculatePriorities() {
        User user = memberRepository.findUserWithDetailsByUserId(1); // 쿼리 한 번

        // 1) isComplete==false인 게시물 && 자신(user)이 작성한 게시물 제외 - 쿼리 한 번
        List<RecruitPost> recommendedPostList = recruitPostRepository.findAllByIsCompleteAndUserIdNot(false, user);
        for (RecruitPost recruitPost : recommendedPostList) {
            System.out.println("recruitPost.getRecruitPostId() = " + recruitPost.getRecruitPostId());
        }

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
        for (String userInterestField : userInterestFieldList) {
            System.out.println("userInterestField = " + userInterestField);
        }
        for (String userLanguage : userLanguageList) {
            System.out.println("userLanguage = " + userLanguage);
        }
        for (String userPersonality : userPersonalityList) {
            System.out.println("userPersonality = " + userPersonality);
        }

        // 각 게시물에 대한 우선순위 부여
        HashMap<RecruitPost, Integer> priorityMap = new HashMap<RecruitPost, Integer>();
        recommendedPostList.forEach(post -> {
            int priority = 0;
            // 여기서 언어, 관심분야, 성격 일치하는지 확인하여 우선순위 계산
            // 쿼리가 post 수 만큼 날라가게 개선
            priority += calculatePriorityForMatchingAttributes(post, userLanguageList, userInterestFieldList, userPersonalityList);
            priorityMap.put(post, priority);
        });
        // priorityMap 출력
        Iterator<RecruitPost> iter = priorityMap.keySet().iterator();
        while (iter.hasNext()) {
            RecruitPost key = iter.next();
            Integer value = (Integer) priorityMap.get(key);

            System.out.println(key + " : " + value);
        }

        // priorityMap을 값으로 정렬하여 상위 4개 선택 (우선순위 높은 4개)
        List<RecruitPost> topPosts = selectTopPriorityPosts(priorityMap, 4);
        for (RecruitPost topPost : topPosts) {
            System.out.println("topPost.getRecruitPostId() = " + topPost.getRecruitPostId());
        }


        // DTO 생성 및 반환
        List<RecRecruitPostDTO> result = createRecruitPostDTOList(topPosts);
        for (RecRecruitPostDTO recRecruitPostDTO : result) {
            System.out.println("recRecruitPostDTO.toString() = " + recRecruitPostDTO.toString());
        }
    }

    private int calculatePriorityForMatchingAttributes(RecruitPost post,
                                                       Set<String> userLanguageList,
                                                       Set<String> userInterestFieldList,
                                                       Set<String> userPersonalityList) {
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

    private List<RecRecruitPostDTO> createRecruitPostDTOList(List<RecruitPost> topPosts) {
        // response 생성
        List<RecRecruitPostDTO> recommendPostsResponse = new ArrayList<>();

        // null 처리?

        for (RecruitPost topPost : topPosts) {
            // 모집글 정보
            int postId = topPost.getRecruitPostId();
            MajorLectureEnum majorLectureName = topPost.getMajorLectureName();
            String title = topPost.getTitle();
            Set<String> recruitLanguages = topPost.getRecruitLanguages()
                                                    .stream()
                                                    .map(recruitLanguage -> recruitLanguage.getLanguage().name()).collect(Collectors.toSet());
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
            String createdDate = simpleDateFormat.format(topPost.getCreatedDate());

            // 모집글 작성자(팀장) 정보 - 쿼리 4개
            User teamLeader = topPost.getUserId();
            DonggaeRank donggaeRank = userRankRepository.findUserRankByUserId(teamLeader).getRankName();
            BaekjoonRank bojRank = teamLeader.getBoj_rank();
            String userName = teamLeader.getGithubName();

            RecRecruitPostDTO recRecruitPostDTO = RecRecruitPostDTO.builder()
                    .recruitPostId(postId)
                    .majorLectureName(majorLectureName.label())
                    .title(title)
                    .recruitLanguages(recruitLanguages)
                    .donggaeRank(donggaeRank.label())
                    .bojRank(bojRank.label())
                    .userName(userName)
                    .createDate(createdDate)
                    .build();

            recommendPostsResponse.add(recRecruitPostDTO);
        }

        return recommendPostsResponse;
    }
}
