package Otwos.Donggae.domain.RecruitPost.service;

import Otwos.Donggae.DAO.Recruit.RecruitPost;
import Otwos.Donggae.DTO.RecruitPost.RecruitPostDTO;
import Otwos.Donggae.DTO.RecruitPost.RecruitPostRequestDTO;
import Otwos.Donggae.domain.RecruitPost.Repository.RecruitPostRepository;
import Otwos.Donggae.domain.member.repository.MemberRepository;
import Otwos.Donggae.domain.member.service.EmailService;
import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;

@Service
public class RecruitPostServiceImpl implements RecruitPostService {

    @Autowired
    private RecruitPostService recruitPostService;
    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private RecruitPostRepository  recruitPostRepository;

    @Transactional
    public RecruitPostDTO createBoard(RecruitPostRequestDTO recruitPostRequestDTO, Authentication authentication){
        String title= recruitPostRequestDTO.getTitle();
        String content=recruitPostRequestDTO.getContent();

        int userId = memberRepository.findById(authentication.getId()).getId();

        RecruitPost recruitPost = new RecruitPost();
        recruitPost.setContent(content);
        recruitPost.setCreatedDate(new Timestamp(System.currentTimeMillis()));
        recruitPost.setUserId(memberRepository.findUserByGithubName(authentication.getUser()));
        recruitPost.setTitle(title);
        RecruitPost savePost = recruitPostService.save(recruitPost);

        return new RecruitPostDTO(savePost,userId);
    }

    @Transactional
    public String deleteBoard(int recruitPostId,Authentication authentication){
        RecruitPost recruitPost=recruitPostRepository.findRecruitPostByRecruitPostId(recruitPostId);
        if(recruitPost.getUserId() == memberRepository.findUserByGithubName(authentication.getName()).getId()){
            recruitPostRepository.deleteById(recruitPostId);
            return "success";
        }
        return "fail";
    }
    @Transactional
    public RecruitPostDTO editRecruitPost(int recruitPostId, RecruitPostRequestDTO recruitPostRequestDTO){
        RecruitPost recruitPost = recruitPostRepository.findRecruitPostByRecruitPostId(recruitPostId);

        if(recruitPostRequestDTO.getTitle()!=null){
            recruitPost.setTitle(recruitPostRequestDTO.getTitle());
        }
        if(recruitPostRequestDTO.getContent()!=null){
            recruitPost.setContent(recruitPostRequestDTO.getContent());
        }
        recruitPostRepository.save(recruitPost);

        return new RecruitPostDTO(recruitPost);
    }
}
