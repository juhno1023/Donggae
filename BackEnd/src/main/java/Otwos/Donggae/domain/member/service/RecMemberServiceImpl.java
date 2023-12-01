package Otwos.Donggae.domain.member.service;

import Otwos.Donggae.DAO.User.User;
import Otwos.Donggae.DTO.team.RecMemberDTO;
import Otwos.Donggae.Global.Rank.BaekjoonRank;
import Otwos.Donggae.domain.member.repository.MemberRepository;
import Otwos.Donggae.domain.member.repository.info.UserInterestFieldRepository;
import Otwos.Donggae.domain.member.repository.info.UserLanguageRepository;
import Otwos.Donggae.domain.member.repository.info.UserPersonalityRepository;
import Otwos.Donggae.domain.member.repository.info.UserStudyFieldRepository;
import Otwos.Donggae.domain.rank.repository.UserRankRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class RecMemberServiceImpl implements RecMemberService {

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private UserRankRepository userRankRepository;

    @Autowired
    private UserLanguageRepository userLanguageRepository;

    @Autowired
    private UserInterestFieldRepository userInterestFieldRepository;

    @Autowired
    private UserPersonalityRepository userPersonalityRepository;

    @Autowired
    private UserStudyFieldRepository userStudyFieldRepository;


    @Override
    public List<RecMemberDTO> recommendMember(int userid) {

        User currentUser = memberRepository.findUserWithDetailsByUserId(userid);

        // 사용자 정보 가져오기
        Set<String> userLanguages = currentUser.getUserLanguages()
                .stream()
                .map(lang -> lang.getLanguage().name())
                .collect(Collectors.toSet());

        Set<String> userInterestFields = currentUser.getUserInterestFields()
                .stream()
                .map(field -> field.getInterestField().name())
                .collect(Collectors.toSet());

        Set<String> userPersonalities = currentUser.getUserPersonalities()
                .stream()
                .map(personality -> personality.getPersonality().name())
                .collect(Collectors.toSet());

        Set<String> userStudyFields = currentUser.getUserStudyFields()
                .stream()
                .map(studyField -> studyField.getStudyField().name())
                .collect(Collectors.toSet());

        // 모든 사용자 가져오기
        List<User> allUsers = memberRepository.findAll();

        // 평균 devTestScore 계산
        double devTestScoreAvg = allUsers.stream()
                .mapToInt(User::getDevTestScore)
                .average()
                .orElse(0.0);

        // 각 사용자에 대한 우선순위 부여
        HashMap<User, Integer> priorityMap = calculatePriorities(currentUser, allUsers, userLanguages, userInterestFields, userPersonalities, userStudyFields, devTestScoreAvg);

        // 2명 추천
        List<User> topUsers = selectTopPriorityUsers(priorityMap, 2);

        // DTO 생성 및 반환
        return createRecMemberDTOList(topUsers);
    }

    private HashMap<User, Integer> calculatePriorities(User currentUser, List<User> allUsers, Set<String> userLanguages, Set<String> userInterestFields, Set<String> userPersonalities, Set<String> userStudyFields, double devTestScoreAvg) {
        // 사용자들의 우선순위를 저장하는 map
        HashMap<User, Integer> priorityMap = new HashMap<>();
        allUsers.forEach(user -> {
            if (!user.equals(currentUser)) { // 현재 사용자는 제외
                int priority = 0;
                // 여기서 언어, 관심분야, 성격, 전공 일치하는지 확인하여 우선순위 계산
                priority += calculatePriorityForMatchingAttributes(user, userLanguages, userInterestFields, userPersonalities, userStudyFields, devTestScoreAvg);
                priorityMap.put(user, priority);
            }
        });
        return priorityMap;
    }

    private int calculatePriorityForMatchingAttributes(User user, Set<String> userLanguages, Set<String> userInterestFields, Set<String> userPersonalities, Set<String> userStudyFields, double devTestScoreAvg) {
        int priority = 0;
        if (user.getUserLanguages().stream().anyMatch(lang -> userLanguages.contains(lang.getLanguage().name()))) {
            priority++;
        }
        if (user.getUserInterestFields().stream().anyMatch(field -> userInterestFields.contains(field.getInterestField().name()))) {
            priority++;
        }
        if (user.getUserPersonalities().stream().anyMatch(pers -> userPersonalities.contains(pers.getPersonality().name()))) {
            priority++;
        }
        if (user.getUserStudyFields().stream().anyMatch(studyField -> userStudyFields.contains(studyField.getStudyField().name()))) {
            priority++;
        }
        // devTestScore가 평균 이상인 경우 우선 순위 증가
        if (user.getDevTestScore() > devTestScoreAvg) {
            priority++;
        }
        return priority;
    }

    private List<RecMemberDTO> createRecMemberDTOList(List<User> topUsers) {
        // response 생성
        List<RecMemberDTO> recommendMembersResponse = new ArrayList<>();

        for (User topUser : topUsers) {
            // 사용자 정보
            int userid = topUser.getUserId();
            String githubName = topUser.getGithubName();
            String intro = topUser.getIntro();
            int teamExpCount = topUser.getTeamExpCount();
            int leaderCount = topUser.getLeaderCount();
            BaekjoonRank bojRank = topUser.getBoj_rank();
            int devTestScore = topUser.getDevTestScore();
            List<String> userLanguages = topUser.getUserLanguages()
                    .stream()
                    .map(userLanguage -> userLanguage.getLanguage().name()).toList();

            List<String> userPersonalities = topUser.getUserPersonalities()
                    .stream()
                    .map(userPersonality -> userPersonality.getPersonality().name()).toList();

            List<String> userInterestFields = topUser.getUserInterestFields()
                    .stream()
                    .map(userInterestField -> userInterestField.getInterestField().name()).toList();

            List<String> userStudyFields = topUser.getUserStudyFields()
                    .stream()
                    .map(userStudyField -> userStudyField.getStudyField().name()).toList();

            RecMemberDTO recMemberDTO = new RecMemberDTO(userid, githubName, intro, teamExpCount, leaderCount, bojRank.label(), devTestScore, userLanguages, userPersonalities, userInterestFields, userStudyFields);
            recommendMembersResponse.add(recMemberDTO);
        }

        return recommendMembersResponse;
    }

    private static List<User> selectTopPriorityUsers(Map<User, Integer> priorityMap, int limitSize) {
        List<User> topPriorityUsers = priorityMap.entrySet()
                .stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .limit(limitSize * 2) // 추천할 개수의 두 배를 추출
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());

        // 추출된 사용자들을 섞는다.
        Collections.shuffle(topPriorityUsers);

        // 섞은 후 상위 N명만 선택
        return topPriorityUsers.stream().limit(limitSize).collect(Collectors.toList());
    }
}
