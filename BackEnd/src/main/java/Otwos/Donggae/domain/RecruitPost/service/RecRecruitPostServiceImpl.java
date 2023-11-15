package Otwos.Donggae.domain.RecruitPost.service;

import Otwos.Donggae.DAO.Recruit.RecruitLanguage;
import Otwos.Donggae.DAO.Recruit.RecruitPost;
import Otwos.Donggae.DAO.User.*;
import Otwos.Donggae.DTO.RecruitPost.RecRecruitPostDTO;
import Otwos.Donggae.Global.LanguageEnum;
import Otwos.Donggae.Global.MajorLectureEnum;
import Otwos.Donggae.Global.Rank.BaekjoonRank;
import Otwos.Donggae.Global.Rank.DonggaeRank;
import Otwos.Donggae.domain.RecruitPost.Repository.RecruitPostRepository;
import Otwos.Donggae.domain.RecruitPost.Repository.info.RecruitFieldRepository;
import Otwos.Donggae.domain.RecruitPost.Repository.info.RecruitLanguageRepository;
import Otwos.Donggae.domain.RecruitPost.Repository.info.RecruitPersonalityRepository;
import Otwos.Donggae.domain.member.repository.MemberRepository;
import Otwos.Donggae.domain.member.repository.info.UserInterestFieldRepository;
import Otwos.Donggae.domain.member.repository.info.UserLanguageRepository;
import Otwos.Donggae.domain.member.repository.info.UserPersonalityRepository;
import Otwos.Donggae.domain.rank.repository.UserRankRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.*;
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
    private UserRankRepository userRankRepository;

    @Override
    public List<RecRecruitPostDTO> recommendRecruitPost(int userId) {
        // user 정보 가져오기 - 관심분야, 언어, 성격
        User user = memberRepository.findUserByUserId(userId);
        List<UserInterestField> userInterestFieldList = userInterestFieldRepository.findAllByUserId(user);
        List<UserLanguage> userLanguageList = userLanguageRepository.findAllByUserId(user);
        List<UserPersonality> userPersonalityList = userPersonalityRepository.findAllByUserId(user);

        // 1) isComplete==false인 게시물 && 자신(user)이 작성한 게시물 제외
        List<RecruitPost> recommendedPostList = recruitPostRepository.findAllByIsCompleteAndUserIdNot(false, user);

        // RecruitPost의 우선순위를 저장하는 map
        HashMap<RecruitPost, Integer> priorityMap = new HashMap<RecruitPost, Integer>();
        for (RecruitPost post : recommendedPostList) {
            priorityMap.put(post, 0); // 초기화
        }

        // 2) 언어 일치하는 recruit post 우선순위 부여
        for (UserLanguage userLanguage : userLanguageList) {
            for (RecruitPost recPost : recommendedPostList) {
                if(recPost.getRecruitLanguages().contains(userLanguage.getLanguage())){ // user의 사용 언어가 선호 언어로 포함된 게시물
                    priorityMap.put(recPost, priorityMap.get(recPost) + 1); // 우선순위 1 증가
                }
            }
        }

        // 3) 관심분야 일치하는 recruit post 우선순위 부여
        for (UserInterestField userInterestField : userInterestFieldList) {
            for (RecruitPost recPost : recommendedPostList) {
                if(recPost.getRecruitFields().contains(userInterestField.getInterestField())){ // user의 관심 분야가 모집 분야로 포함된 게시물
                    priorityMap.put(recPost, priorityMap.get(recPost) + 1); // 우선순위 1 증가
                }
            }
        }

        // 4) 성격 일치하는 recruit post 우선순위 부여
        for (UserPersonality userPersonality : userPersonalityList) {
            for (RecruitPost recPost : recommendedPostList) {
                if(recPost.getRecruitFields().contains(userPersonality.getPersonality())){ // user의 성격이 선호 성향으로 포함된 게시물
                    priorityMap.put(recPost, priorityMap.get(recPost) + 1); // 우선순위 1 증가
                }
            }
        }

        // priorityMap을 값으로 정렬하여 상위 4개 선택 (우선순위 높은 4개)
        List<RecruitPost> top4RecruitPosts = priorityMap.entrySet()
                .stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .limit(4)
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());

        // 전체 게시물이 4개가 안될 때 - 처리 해야 하나?? or 추천 게시물 수도 response로?

        // response 생성
        List<RecRecruitPostDTO> recommendPostsResponse = new ArrayList<>();

        // null 처리?

        for (RecruitPost top4RecruitPost : top4RecruitPosts) {
            // 모집글 정보
            int postId = top4RecruitPost.getRecruitPostId();
            MajorLectureEnum majorLectureName = top4RecruitPost.getMajorLectureName();
            String title = top4RecruitPost.getTitle();
            List<RecruitLanguage> recruitLanguages = top4RecruitPost.getRecruitLanguages();
            Timestamp createdDate = top4RecruitPost.getCreatedDate();

            // 모집글 작성자(팀장) 정보
            User teamLeader = top4RecruitPost.getUserId();
            DonggaeRank donggaeRank = userRankRepository.findUserRankByUserId(teamLeader).getRankName();
            BaekjoonRank bojRank = teamLeader.getBoj_rank();
            String userName = teamLeader.getGithubName();

            RecRecruitPostDTO recRecruitPostDTO = new RecRecruitPostDTO(postId, majorLectureName, title, recruitLanguages, donggaeRank, bojRank, userName, createdDate);
            recommendPostsResponse.add(recRecruitPostDTO);
        }

        return recommendPostsResponse;
    }
}
