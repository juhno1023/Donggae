package Otwos.Donggae.domain.RecruitPost.service;

import Otwos.Donggae.DAO.Recruit.RecruitField;
import Otwos.Donggae.DAO.Recruit.RecruitLanguage;
import Otwos.Donggae.DAO.Recruit.RecruitPersonality;
import Otwos.Donggae.DAO.Recruit.RecruitPost;
import Otwos.Donggae.DAO.Team.Team;
import Otwos.Donggae.DAO.User.User;
import Otwos.Donggae.DTO.RecruitPost.RecRecruitPostDTO;
import Otwos.Donggae.DTO.RecruitPost.RecruitPostDTO;
import Otwos.Donggae.DTO.RecruitPost.RecruitPostRequestDTO;
import Otwos.Donggae.DTO.RecruitPost.recruitPostInfo.RecruitFieldDTO;
import Otwos.Donggae.DTO.RecruitPost.recruitPostInfo.RecruitLanguageDTO;
import Otwos.Donggae.DTO.RecruitPost.recruitPostInfo.RecruitPersonalityDTO;
import Otwos.Donggae.DTO.team.TeamDTO;
import Otwos.Donggae.DTO.team.TeamMemberDTO;
import Otwos.Donggae.DTO.team.teamDetail.TeamIdRequest;
import Otwos.Donggae.Global.FieldEnum;
import Otwos.Donggae.Global.LanguageEnum;
import Otwos.Donggae.Global.MajorLectureEnum;
import Otwos.Donggae.Global.PersonalityEnum;
import Otwos.Donggae.domain.RecruitPost.Repository.RecruitPostRepository;
import Otwos.Donggae.domain.RecruitPost.Repository.info.RecruitFieldRepository;
import Otwos.Donggae.domain.RecruitPost.Repository.info.RecruitLanguageRepository;
import Otwos.Donggae.domain.RecruitPost.Repository.info.RecruitPersonalityRepository;
import Otwos.Donggae.domain.member.repository.MemberRepository;
import Otwos.Donggae.domain.team.repository.TeamMemberRepository;
import Otwos.Donggae.domain.team.repository.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static java.sql.Types.NULL;


@Service
public class RecruitPostServiceImpl implements RecruitPostService {
    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private RecruitPostRepository recruitPostRepository;

    @Autowired
    private RecruitLanguageRepository recruitLanguageRepository;

    @Autowired
    private RecruitPersonalityRepository recruitPersonalityRepository;
    @Autowired
    private RecruitFieldRepository recruitFieldRepository;

    @Autowired
    private TeamRepository teamRepository;

    @Autowired
    private TeamMemberRepository teamMemberRepository;

    @Transactional //게시글 작성
    public void createRecruitPostAndTeam(RecruitPostRequestDTO recruitPostRequestDTO, int userId) {

        User user = memberRepository.findUserByUserId(userId);
        String title = recruitPostRequestDTO.getTitle();
        String content = recruitPostRequestDTO.getContent();
        Timestamp createdDate = new Timestamp(System.currentTimeMillis());
        MajorLectureEnum majorLectureName = recruitPostRequestDTO.getMajorLectureName();
        String teamName = recruitPostRequestDTO.getTeamName();

        RecruitPostDTO recruitPostDTO = new RecruitPostDTO(
                NULL,
                user,
                title,
                content,
                majorLectureName,
                createdDate,
                null,
                null,
                null,
                null
        );

        String tempTitle = title;

        recruitPostRepository.save(recruitPostDTO.toEntity());

        RecruitPost recruitPost = recruitPostRepository.findRecruitPostByTitle(tempTitle);

        List<RecruitLanguageDTO> recruitLanguageDTOS = addRecruitPostLanguageDTO(recruitPost, recruitPostRequestDTO);
        //게시글 모집 언어를 추가하기위한 DTO 리스트

        List<RecruitLanguage> recruitLanguages = convertToRecruitLanguageEntities(recruitLanguageDTOS, tempTitle);
        //게시글 모집 언어를 추가하기위한 DTO 리스트를 엔티티로 변환

        List<RecruitFieldDTO> recruitFieldDTOS = addRecruitPostFieldDTO(recruitPost, recruitPostRequestDTO);
        //게시글 모집 분야를 추가하기위한 DTO 리스트

        List<RecruitField> recruitFields = convertToRecruitFieldEntities(recruitFieldDTOS, tempTitle);
        //게시글 모집 언어를 추가하기위한 DTO 리스트를 엔티티로 변환

        List<RecruitPersonalityDTO> recruitPersonalityDTOS = addRecruitPostPersonalityDTO(recruitPost, recruitPostRequestDTO);
        //게시글 모집 성향를 추가하기위한 DTO 리스트

        List<RecruitPersonality> recruitPersonalities = convertToRecruitPersonalityEntities(recruitPersonalityDTOS, tempTitle);
        //게시글 모집 언어를 추가하기위한 DTO 리스트를 엔티티로 변환

        recruitLanguageRepository.saveAll(recruitLanguages);
        //저장
        recruitFieldRepository.saveAll(recruitFields);
        //저장
        recruitPersonalityRepository.saveAll(recruitPersonalities);
        //저장

        int recruitPostId = recruitPost.getRecruitPostId();

        TeamDTO teamDTO = new TeamDTO(
                NULL,
                recruitPostId,
                teamName
        );
        // Team 저장
        teamRepository.save(teamDTO.toEntity(recruitPost));

        Team team = teamRepository.findTeamByTeamName(teamName);

        TeamMemberDTO teamMemberDTO = new TeamMemberDTO(
                team.getTeamId(),
                user.getUserId(),
                Boolean.TRUE
        );
        // Team Member 저장
        teamMemberRepository.save(teamMemberDTO.toEntity(team, user));
    }

    private List<RecruitLanguageDTO> addRecruitPostLanguageDTO(RecruitPost recruitPost, RecruitPostRequestDTO recruitPostRequestDTO){
        List<RecruitLanguageDTO> recruitLanguageDTOS = new ArrayList<>(); //빈 리스트 생성
        List<String> recruitLanguages = recruitPostRequestDTO.getRecruitLanguages();

        for (String language : recruitLanguages) {
            // 문자열을 Enum으로 변환
            LanguageEnum languageEnum = LanguageEnum.valueOf(language);

            RecruitLanguageDTO recruitLanguageDTO = new RecruitLanguageDTO(
                    recruitPost.getRecruitPostId(),
                    languageEnum
            );
            recruitLanguageDTOS.add(recruitLanguageDTO);
        }
        return recruitLanguageDTOS;
    }

    private List<RecruitLanguage> convertToRecruitLanguageEntities(List<RecruitLanguageDTO> recruitLanguageDTOS, String title) {
        return recruitLanguageDTOS.stream()
                .map(dto -> {
                    RecruitPost recruitPost = recruitPostRepository.findRecruitPostByTitle(title);
                    return new RecruitLanguage(recruitPost, dto.getLanguage());
                })
                .collect(Collectors.toList());
    }

    private List<RecruitFieldDTO> addRecruitPostFieldDTO(RecruitPost recruitPost, RecruitPostRequestDTO recruitPostRequestDTO){
        List<RecruitFieldDTO> recruitFieldDTOS = new ArrayList<>(); //빈 리스트 생성
        List<String> recruitFields = recruitPostRequestDTO.getRecruitFields();

        for (String field : recruitFields) {
            // 문자열을 Enum으로 변환
            FieldEnum fieldEnum = FieldEnum.valueOf(field);

            RecruitFieldDTO recruitFieldDTO = new RecruitFieldDTO(
                    recruitPost.getRecruitPostId(),
                    fieldEnum
            );
            recruitFieldDTOS.add(recruitFieldDTO);
        }
        return recruitFieldDTOS;
    }

    private List<RecruitField> convertToRecruitFieldEntities(List<RecruitFieldDTO> recruitFieldDTOS, String title) {
        return recruitFieldDTOS.stream()
                .map(dto -> {
                    RecruitPost recruitPost = recruitPostRepository.findRecruitPostByTitle(title);
                    return new RecruitField(recruitPost, dto.getField());
                })
                .collect(Collectors.toList());
    }

    private List<RecruitPersonalityDTO> addRecruitPostPersonalityDTO(RecruitPost recruitPost, RecruitPostRequestDTO recruitPostRequestDTO){
        List<RecruitPersonalityDTO> recruitPersonalityDTOS = new ArrayList<>(); //빈 리스트 생성
        List<String> recruitPersonalities = recruitPostRequestDTO.getRecruitPersonalities();

        for (String personality : recruitPersonalities) {
            // 문자열을 Enum으로 변환
            PersonalityEnum personalityEnum = PersonalityEnum.valueOf(personality);

            RecruitPersonalityDTO recruitPersonalityDTO = new RecruitPersonalityDTO(
                    recruitPost.getRecruitPostId(),
                    personalityEnum
            );
            recruitPersonalityDTOS.add(recruitPersonalityDTO);
        }
        return recruitPersonalityDTOS;
    }

    private List<RecruitPersonality> convertToRecruitPersonalityEntities(List<RecruitPersonalityDTO> recruitPersonalityDTOS, String title) {
        return recruitPersonalityDTOS.stream()
                .map(dto -> {
                    RecruitPost recruitPost = recruitPostRepository.findRecruitPostByTitle(title);
                    return new RecruitPersonality(recruitPost, dto.getPersonality());
                })
                .collect(Collectors.toList());
    }

    @Transactional //게시글 삭제
    public String deleteRecruitPost(int recruitPostId, int userId){
        User user = memberRepository.findUserByUserId(userId);
        RecruitPost recruitPost = recruitPostRepository.findRecruitPostByRecruitPostId(recruitPostId);
        if (recruitPost.getUserId() == user) {
            recruitPostRepository.deleteById(recruitPostId);
            return "success";
        }
        return "fail";
    }

    @Transactional //게시글 수정
    public void editRecruitPost(int recruitPostId, RecruitPostRequestDTO recruitPostRequestDTO, int userId){
        RecruitPost recruitPost = recruitPostRepository.findRecruitPostByRecruitPostId(recruitPostId);
        if(recruitPostRequestDTO.getTitle()!=null){
            recruitPost.setTitle(recruitPostRequestDTO.getTitle());
        }
        if(recruitPostRequestDTO.getContent()!=null){
            recruitPost.setContent(recruitPostRequestDTO.getContent());
        }
        if(recruitPostRequestDTO.getMajorLectureName()!=null){
            recruitPost.setMajorLectureName(recruitPostRequestDTO.getMajorLectureName());
        }

        recruitPostRepository.save(recruitPost);
    }

    //    @Override // 게시글 조회
//    public RecruitPostResponseDTO getRecruitPost(int recruitPostId) {
//
//    }

    @Transactional
    @Override
    public void completeRecruitPost(TeamIdRequest teamIdRequest) {
        Team team = teamRepository.findTeamByTeamId(teamIdRequest.getTeamId());
        RecruitPost recruitPost = team.getRecruitPostId();
        try {
            validateTeamAndPost(team, recruitPost);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        recruitPost.complete();
        recruitPostRepository.save(recruitPost); // 변경사항 저장
    }

    private void validateTeamAndPost(Team team, RecruitPost recruitPost) throws Exception{
        if (team == null) {
            throw new Exception("team is null");
        }
        if (recruitPost == null) {
            throw new Exception("recruitPost is null");
        }
    }

}


