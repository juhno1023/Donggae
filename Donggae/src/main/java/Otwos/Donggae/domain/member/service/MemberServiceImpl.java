package Otwos.Donggae.domain.member.service;

import static java.sql.Types.NULL;

import Otwos.Donggae.DAO.User.User;
import Otwos.Donggae.DTO.member.UserDTO;
import Otwos.Donggae.DTO.member.register.SignUpDTO;
import Otwos.Donggae.Global.Rank.BaekjoonRank;
import Otwos.Donggae.domain.member.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MemberServiceImpl implements MemberService{
    private MemberRepository memberRepository;

    @Autowired
    public MemberServiceImpl(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    //회원가입 로직
    @Override
    public void register(SignUpDTO signUpDTO){
        // github name, dgu email 중복 검사
        try {
            validateUser(signUpDTO);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        // 중복되면 회원가입 X
        UserDTO userDTO = new UserDTO(
                NULL,
                signUpDTO.getGithubName(),
                null,
                NULL,
                NULL,
                BaekjoonRank.Unrated, // 회원가입할때 기본으로 Unrated 주입
                NULL,
                signUpDTO.getDguEmail()
        );
        memberRepository.save(userDTO.toEntity());
    }

    // github name, dgu email 중복검사
    private void validateUser(SignUpDTO signUpDTO) throws Exception {
        User userByGithubName = memberRepository.findUserByGithubName(signUpDTO.getGithubName());
        User userByDguEmail = memberRepository.findUserByDguEmail(signUpDTO.getDguEmail());
        if (userByGithubName != null) {
            throw new Exception("GitHub 이름이 중복됩니다: " + signUpDTO.getGithubName());
        }
        if (userByDguEmail != null) {
            throw new Exception("DGU 이메일이 중복됩니다: " + signUpDTO.getDguEmail());
        }
    }

    public String checkUserSignUp(String githubId){
        User userByGithubName = memberRepository.findUserByGithubName(githubId);
        if(userByGithubName == null){
            return null;
        }
        else{
            return userByGithubName.getGithubName();
        }
    }
}
