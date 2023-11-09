package Otwos.Donggae.domain.application.service;

import Otwos.Donggae.DAO.Recruit.RecruitPost;
import Otwos.Donggae.DAO.User.User;
import Otwos.Donggae.DTO.application.ApplyDTO;
import Otwos.Donggae.domain.application.repository.ApplicationRepository;
import Otwos.Donggae.domain.member.repository.MemberRepository;
import org.springframework.stereotype.Service;

@Service
public class ApplicationServiceImpl implements ApplicationService{
    private ApplicationRepository applicationRepository;
    private MemberRepository memberRepository;
    private RecruitPostRepository recruitPostRepository;

    public ApplicationServiceImpl(ApplicationRepository applicationRepository, MemberRepository memberRepository
                                    , RecruitPostRepository recruitPostRepository) {
        this.applicationRepository = applicationRepository;
        this.memberRepository = memberRepository;
        this.recruitPostRepository = recruitPostRepository;
    }

    @Override
    public void applyFor(ApplyDTO applyDTO) {
        User user = memberRepository.findUserByUserId(applyDTO.getUserId());
        RecruitPost recruitPost = recruitPostRepository.findRecruitPostByRecruitPostId(applyDTO.getRecruitPostId());
        //예외처리 하고 저장
        try {
            validateUserAndRecruitPost(user, recruitPost);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        //해당 user와 recruitPost가 있으면 저장
        //applicationId따로 안넣어도 알아서 들어가겠지?
        applicationRepository.save(applyDTO.toEntity(user, recruitPost));
    }

    private void validateUserAndRecruitPost(User user, RecruitPost recruitPost) throws Exception {
        if (user == null) {
            throw new Exception("해당 user가 없습니다.");
        }
        if (recruitPost == null) {
            throw new Exception("해당 recruitPost가 없습니다.");
        }
    }
}
