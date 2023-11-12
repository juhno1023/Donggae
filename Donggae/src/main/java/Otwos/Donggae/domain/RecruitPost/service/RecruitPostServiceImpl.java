package Otwos.Donggae.domain.RecruitPost.service;

import Otwos.Donggae.DAO.Recruit.RecruitLanguage;
import Otwos.Donggae.DAO.Recruit.RecruitPost;
import Otwos.Donggae.DAO.User.User;
import Otwos.Donggae.DAO.User.UserLanguage;
import Otwos.Donggae.DTO.RecruitPost.RecruitPostDTO;
import Otwos.Donggae.DTO.RecruitPost.RecruitPostRequestDTO;
import Otwos.Donggae.DTO.RecruitPost.recruitPostInfo.RecruitLanguageDTO;
import Otwos.Donggae.DTO.member.userinfo.UserLanguageDTO;
import Otwos.Donggae.Global.MajorLectureEnum;
import Otwos.Donggae.domain.RecruitPost.Repository.RecruitPostRepository;
import Otwos.Donggae.domain.RecruitPost.Repository.info.RecruitLanguageRepository;
import Otwos.Donggae.domain.member.repository.MemberRepository;
import Otwos.Donggae.domain.member.service.EmailService;
import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

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

    @Transactional
    public void createRecruitPost(RecruitPostRequestDTO recruitPostRequestDTO, int userId) {

        String title = recruitPostRequestDTO.getTitle();
        String content = recruitPostRequestDTO.getContent();
        User user = memberRepository.findUserByUserId(userId);
        Timestamp createdDate = new Timestamp(System.currentTimeMillis());
        MajorLectureEnum majorLectureName = recruitPostRequestDTO.getMajorLectureName();

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

        recruitPostRepository.save(recruitPostDTO.toEntity());

//        RecruitPost recruitPost = recruitPostRepository.save(recruitPostDTO.toEntity());
//
//        int postId = recruitPost.getRecruitPostId();
//
//        List<RecruitLanguageDTO> recruitLanguageDTOS = new ArrayList<>();
//        List<RecruitLanguage> recruitLanguages = recruitPostRequestDTO.getRecruitLanguages();
//
//        for (RecruitLanguageDTO recruitLanguageDTO : recruitPostRequestDTO.getRecruitLanguages()) {
//            recruitLanguageDTO.setRecruitPostId(recruitPost);  // Set the RecruitPost for each RecruitLanguageDTO
//            recruitLanguageDTOS.add(recruitLanguageDTO);
//        }
//
//        // 원하는 작업 수행
//        for (RecruitLanguageDTO dto : recruitLanguageDTOS) {
//            // 게시글 ID(postId) 및 언어 정보(dto)를 사용하여 원하는 작업 수행
//        }
////        List<RecruitLanguageDTO> recruitLanguageDTOS = // your list
////                recruitLanguageDTOS.forEach(dto -> {
////                    RecruitLanguage recruitLanguage = dto.toEntity();
////                    // 원하는 작업 수행
////                });
//
//        recruitLanguageRepository.saveAll(recruitLanguageDTO);
//    }

//    @Transactional
//    public String deleteBoard(int recruitPostId,Authentication authentication){
//        RecruitPost recruitPost=recruitPostRepository.findRecruitPostByRecruitPostId(recruitPostId);
//        if(recruitPost.getUserId() == memberRepository.findUserByGithubName(authentication.getName()).getId()){
//            recruitPostRepository.deleteById(recruitPostId);
//            return "success";
//        }
//        return "fail";
//    }
//    @Transactional
//    public RecruitPostDTO editRecruitPost(int recruitPostId, RecruitPostRequestDTO recruitPostRequestDTO){
//        RecruitPost recruitPost = recruitPostRepository.findRecruitPostByRecruitPostId(recruitPostId);
//
//        if(recruitPostRequestDTO.getTitle()!=null){
//            recruitPost.setTitle(recruitPostRequestDTO.getTitle());
//        }
//        if(recruitPostRequestDTO.getContent()!=null){
//            recruitPost.setContent(recruitPostRequestDTO.getContent());
//        }
//        recruitPostRepository.save(recruitPost);
//
//        return new RecruitPostDTO(recruitPost);
//    }
    }
}
