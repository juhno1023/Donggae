package Otwos.Donggae.domain.member.service;

import static java.sql.Types.NULL;

import Otwos.Donggae.DTO.member.UserDTO;
import Otwos.Donggae.DTO.member.register.SignUpDTO;
import Otwos.Donggae.Global.Rank.BaekjoonRank;
import Otwos.Donggae.domain.member.repository.MemberRepository;
import org.springframework.stereotype.Service;

@Service
public class MemberServiceImpl implements MemberService{
    private MemberRepository memberRepository;

    //회원가입 로직
    @Override
    public void register(SignUpDTO signUpDTO) {
        UserDTO userDTO = new UserDTO(
                NULL,
                signUpDTO.getGithubName(),
                "",
                NULL,
                NULL,
                BaekjoonRank.Unrated, // 회원가입할때 기본으로 Unrated 주입
                NULL,
                signUpDTO.getDguEmail()
        );
        memberRepository.save(userDTO.toEntity());
    }
}
