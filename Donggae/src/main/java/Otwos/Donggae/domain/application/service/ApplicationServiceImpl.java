package Otwos.Donggae.domain.application.service;

import Otwos.Donggae.DAO.User.User;
import Otwos.Donggae.DTO.application.ApplyDTO;
import Otwos.Donggae.domain.application.repository.ApplicationRepository;
import Otwos.Donggae.domain.member.repository.MemberRepository;
import org.springframework.stereotype.Service;

@Service
public class ApplicationServiceImpl implements ApplicationService{
    private ApplicationRepository applicationRepository;
    private MemberRepository memberRepository;
    //private RecruitPostRepository recruitPostRepository;

    public ApplicationServiceImpl(ApplicationRepository applicationRepository, MemberRepository memberRepository) {
        this.applicationRepository = applicationRepository;
        this.memberRepository = memberRepository;
    }

    @Override
    public void applyFor(ApplyDTO applyDTO) {
        User user = memberRepository.findUserByUserId(applyDTO.getUserId());
        //RecruitPost recruitPost = recruitPostRepository.findRecruitPostByRecruitPostId(applyDTO.getRecruitPostId());
        //예외처리 하고 저장
    }
}
