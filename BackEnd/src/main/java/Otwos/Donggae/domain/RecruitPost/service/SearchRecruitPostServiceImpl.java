package Otwos.Donggae.domain.RecruitPost.service;

import Otwos.Donggae.DAO.Recruit.RecruitField;
import Otwos.Donggae.DAO.Recruit.RecruitLanguage;
import Otwos.Donggae.DAO.Recruit.RecruitPersonality;
import Otwos.Donggae.DAO.Recruit.RecruitPost;
import Otwos.Donggae.DAO.Team.Team;
import Otwos.Donggae.DAO.Team.TeamMember;
import Otwos.Donggae.DAO.User.UserRank;
import Otwos.Donggae.DTO.RecruitPost.search.LectureRecruitPost;
import Otwos.Donggae.DTO.RecruitPost.search.NaturalRecruitPost;
import Otwos.Donggae.DTO.RecruitPost.search.SearchRequest;
import Otwos.Donggae.DTO.RecruitPost.search.SearchResponse;
import Otwos.Donggae.DTO.member.userinfo.response.UserInterestFieldResponse;
import Otwos.Donggae.DTO.member.userinfo.response.UserLanguageResponse;
import Otwos.Donggae.DTO.member.userinfo.response.UserPersonalityResponse;
import Otwos.Donggae.DTO.team.teamDetail.ApplyMemberPreview;
import Otwos.Donggae.Global.FieldEnum;
import Otwos.Donggae.Global.LanguageEnum;
import Otwos.Donggae.Global.MajorLectureEnum;
import Otwos.Donggae.Global.PersonalityEnum;
import Otwos.Donggae.domain.RecruitPost.Repository.RecruitPostRepository;
import Otwos.Donggae.domain.RecruitPost.Repository.info.RecruitLanguageRepository;
import Otwos.Donggae.domain.rank.repository.UserRankRepository;
import Otwos.Donggae.domain.team.repository.TeamMemberRepository;
import Otwos.Donggae.domain.team.repository.TeamRepository;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SearchRecruitPostServiceImpl implements SearchRecruitPostService{
    @Autowired
    private RecruitPostRepository recruitPostRepository;
    @Autowired
    private RecruitLanguageRepository recruitLanguageRepository;
    @Autowired
    private TeamRepository teamRepository;
    @Autowired
    private TeamMemberRepository teamMemberRepository;
    @Autowired
    private UserRankRepository userRankRepository;

    @Override
    public SearchResponse searchRecruitPost(SearchRequest searchRequest) {
        //language, field, personality (request에서 받은거 모두 들어가야 검색되게)
        Set<LanguageEnum> requestedLanguages = searchRequest.getLanguageS().stream()
                .map(UserLanguageResponse::getLanguage)
                .collect(Collectors.toSet());
        Set<FieldEnum> requestedFields = searchRequest.getFieldS().stream()
                .map(UserInterestFieldResponse::getInterestField)
                .collect(Collectors.toSet());
        Set<PersonalityEnum> requestedPersonalities = searchRequest.getPersonalityS().stream()
                .map(UserPersonalityResponse::getPersonality)
                .collect(Collectors.toSet());

        MajorLectureEnum requestedMajorLecture = searchRequest.getMajorLecture();

        //모든 모집 글 불러옴
        List<RecruitPost> allPosts = recruitPostRepository.findAll();
        //검색에 해당되는 것만 가져오기
        List<RecruitPost> matchingPosts = allPosts.stream()
                .filter(post -> matchesRequest(post, requestedLanguages, requestedFields, requestedPersonalities, requestedMajorLecture))
                .collect(Collectors.toList());

        //majorlecture가 있으면 LectureRecruitPost에, 없으면 NaturalRecruitPost에 넣기
        return convertToSearchResponse(matchingPosts);
    }

    private boolean matchesRequest(RecruitPost post, Set<LanguageEnum> requestedLanguages,
                                   Set<FieldEnum> requestedFields, Set<PersonalityEnum> requestedPersonalities,
                                   MajorLectureEnum requestedMajorLecture) {
        // 여기서 각 게시물이 요청과 맞는지를 확인하는 로직을 구현
        // 예: 모든 요청된 언어, 분야, 성격이 게시글에 포함되어 있는지 확인
        Set<LanguageEnum> postLanguages = post.getRecruitLanguages().stream()
                .map(RecruitLanguage::getLanguage)
                .collect(Collectors.toSet());

        Set<FieldEnum> postFields = post.getRecruitFields().stream()
                .map(RecruitField::getField)
                .collect(Collectors.toSet());

        Set<PersonalityEnum> postPersonalities = post.getRecruitPersonalities().stream()
                .map(RecruitPersonality::getPersonality)
                .collect(Collectors.toSet());

        boolean matchesMajorLecture = (requestedMajorLecture == null) ||
                (post.getMajorLectureName() == requestedMajorLecture);

        return postLanguages.containsAll(requestedLanguages) &&
                postFields.containsAll(requestedFields) &&
                postPersonalities.containsAll(requestedPersonalities) &&
                matchesMajorLecture;
    }

    private SearchResponse convertToSearchResponse(List<RecruitPost> posts) {
        // 여기서 매칭된 게시물 목록을 SearchResponse로 변환하는 로직을 구현
        List<LectureRecruitPost> lectureRecruitPosts = new ArrayList<>();
        List<NaturalRecruitPost> naturalRecruitPosts = new ArrayList<>();

        for (RecruitPost recruitPost : posts) {
            //모집 완료된 글은 표시X
            if (recruitPost.getIsComplete() == Boolean.TRUE) {
                continue;
            }

            //팀장 설정
            ApplyMemberPreview teamLeader = returnTeamLeader(recruitPost);

            //모집 언어들
            List<RecruitLanguage> recruitLanguages = recruitLanguageRepository.findAllByRecruitPostId(recruitPost);
            List<UserLanguageResponse> languages = recruitLanguages.stream()
                    .map(lang -> new UserLanguageResponse(lang.getLanguage()))
                    .collect(Collectors.toList());

            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
            String createdDate = simpleDateFormat.format(recruitPost.getCreatedDate());

            //전공 강의 모집 글
            if (recruitPost.getMajorLectureName() != null) {
                LectureRecruitPost lectureRecruitPost = new LectureRecruitPost(
                        recruitPost.getTitle(), //제목
                        languages, //모집 언어들
                        teamLeader, //팀장
                        createdDate, //작성날짜
                        recruitPost.getMajorLectureName() //해당 강의
                );
                lectureRecruitPosts.add(lectureRecruitPost);
            }
            //일반 모집 글
            else {
                NaturalRecruitPost naturalRecruitPost = new NaturalRecruitPost(
                        recruitPost.getTitle(),
                        languages,
                        teamLeader,
                        createdDate
                );
                naturalRecruitPosts.add(naturalRecruitPost);
            }
        }

        return new SearchResponse(lectureRecruitPosts, naturalRecruitPosts); // 임시 반환 값
    }

    //팀장 정보 반환
    private ApplyMemberPreview returnTeamLeader(RecruitPost recruitPost) {
        Team team = teamRepository.findTeamByRecruitPostId(recruitPost);
        TeamMember teamMember = teamMemberRepository.findTeamMemberByTeamIdAndIsLeader(team, Boolean.TRUE);
        UserRank userRank = userRankRepository.findUserRankByUserId(teamMember.getUserId());

        try {
            validateSearchRequest(team, teamMember, userRank);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        ApplyMemberPreview teamLeader = new ApplyMemberPreview(
                teamMember.getUserId().getUserId(), //userId
                teamMember.getUserId().getGithubName(), //이름
                teamMember.getUserId().getBoj_rank(), //백준랭크
                userRank.getRankName() //동개랭크
        );

        return teamLeader;
    }

    private void validateSearchRequest(Team team, TeamMember teamMember, UserRank userRank) throws Exception{
        if (team == null) {
            throw new Exception("team is null");
        }
        if (teamMember == null) {
            throw new Exception("teamMember is null");
        }
        if (userRank == null) {
            throw new Exception("userRank is null");
        }
    }
}
