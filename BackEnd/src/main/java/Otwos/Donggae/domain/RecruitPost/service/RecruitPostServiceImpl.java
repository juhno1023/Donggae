package Otwos.Donggae.domain.RecruitPost.service;

import Otwos.Donggae.DAO.Recruit.RecruitField;
import Otwos.Donggae.DAO.Recruit.RecruitLanguage;
import Otwos.Donggae.DAO.Recruit.RecruitPersonality;
import Otwos.Donggae.DAO.Recruit.RecruitPost;
import Otwos.Donggae.DAO.Team.Team;
import Otwos.Donggae.DAO.Team.TeamMember;
import Otwos.Donggae.DAO.User.User;
import Otwos.Donggae.DAO.User.UserLanguage;
import Otwos.Donggae.DTO.RecruitPost.RecruitPostDTO;
import Otwos.Donggae.DTO.RecruitPost.RecruitPostRequestDTO;
import Otwos.Donggae.DTO.RecruitPost.RecruitPostResponseDTO;
import Otwos.Donggae.DTO.RecruitPost.recruitPostInfo.RecruitFieldDTO;
import Otwos.Donggae.DTO.RecruitPost.recruitPostInfo.RecruitLanguageDTO;
import Otwos.Donggae.DTO.RecruitPost.recruitPostInfo.RecruitPersonalityDTO;
import Otwos.Donggae.DTO.member.userinfo.UserLanguageDTO;
import Otwos.Donggae.DTO.team.TeamDTO;
import Otwos.Donggae.DTO.team.TeamMemberDTO;
import Otwos.Donggae.Global.MajorLectureEnum;
import Otwos.Donggae.domain.RecruitPost.Repository.RecruitPostRepository;
import Otwos.Donggae.domain.RecruitPost.Repository.info.RecruitFieldRepository;
import Otwos.Donggae.domain.RecruitPost.Repository.info.RecruitLanguageRepository;
import Otwos.Donggae.domain.RecruitPost.Repository.info.RecruitPersonalityRepository;
import Otwos.Donggae.domain.member.repository.MemberRepository;
import Otwos.Donggae.domain.member.service.EmailService;
import Otwos.Donggae.domain.team.repository.TeamMemberRepository;
import Otwos.Donggae.domain.team.repository.TeamRepository;
import Otwos.Donggae.domain.team.service.TeamService;
import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
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
    private RecruitPostService recruitPostService;
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
    private TeamService teamService;

    @Autowired
    private TeamRepository teamRepository;

    @Autowired
    private TeamMemberRepository teamMemberRepository;

    @Transactional
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
        List<RecruitLanguage> recruitLanguages = recruitPostRequestDTO.getRecruitLanguages();

        for (RecruitLanguage recruitLanguage : recruitLanguages) {
            RecruitLanguageDTO recruitLanguageDTO = new RecruitLanguageDTO(
                    recruitPost.getRecruitPostId(),
                    recruitLanguage.getLanguage()
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
        List<RecruitField> recruitFields = recruitPostRequestDTO.getRecruitFields();

        for (RecruitField recruitField : recruitFields) {
            RecruitFieldDTO recruitFieldDTO = new RecruitFieldDTO(
                    recruitPost.getRecruitPostId(),
                    recruitField.getField()
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
        List<RecruitPersonality> recruitPersonalities = recruitPostRequestDTO.getRecruitPersonalities();

        for (RecruitPersonality recruitPersonality : recruitPersonalities) {
             RecruitPersonalityDTO recruitPersonalityDTO = new RecruitPersonalityDTO(
                    recruitPost.getRecruitPostId(),
                    recruitPersonality.getPersonality()
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

}


