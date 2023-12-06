package Otwos.Donggae.domain.member.service;

import static java.sql.Types.NULL;

import Otwos.Donggae.DAO.User.User;
import Otwos.Donggae.DTO.member.UserDTO;
import Otwos.Donggae.DTO.member.donggaeRank.UserRankDTO;
import Otwos.Donggae.DTO.member.register.SignUpDTO;
import Otwos.Donggae.DTO.member.register.ValidGithubIdRequest;
import Otwos.Donggae.Global.Rank.BaekjoonRank;
import Otwos.Donggae.Global.Rank.DonggaeRank;
import Otwos.Donggae.domain.member.repository.MemberRepository;
import Otwos.Donggae.domain.rank.repository.UserRankRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MemberServiceImpl implements MemberService{
    private final MemberRepository memberRepository;
    private final UserRankRepository userRankRepository;

    @Autowired
    public MemberServiceImpl(MemberRepository memberRepository, UserRankRepository userRankRepository) {
        this.memberRepository = memberRepository;
        this.userRankRepository = userRankRepository;
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
                0,
                0,
                BaekjoonRank.UNRATED.label(), // 회원가입할때 기본으로 Unrated 주입
                0,
                signUpDTO.getDguEmail(),
                "https://avatars.githubusercontent.com/u/55657581?s=100&v=4" //기본 이미지 프로필로 주입
        );
        memberRepository.save(userDTO.toEntity());
        User user = memberRepository.findUserByDguEmail(signUpDTO.getDguEmail());

        // 동개 랭크도 똥개로 초기 세팅
        UserRankDTO userRankDTO = new UserRankDTO(
                NULL,
                user.getUserId(),
                0,
                DonggaeRank.DDONGGAE.label()
        );
        userRankRepository.save(userRankDTO.toEntity(user));
    }

    @Override
    public void validGithubName(ValidGithubIdRequest request){
        try {
            validateGIt(request);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    // github name 중복검사
    private void validateGIt(ValidGithubIdRequest request) throws Exception {
        User userByGithubName = memberRepository.findUserByGithubName(request.getGithubName());
        if (userByGithubName != null) {
            throw new Exception("GitHub name already exists: " + request.getGithubName());
        }
    }

    // github name, dgu email 중복검사
    private void validateUser(SignUpDTO signUpDTO) throws Exception {
        User userByGithubName = memberRepository.findUserByGithubName(signUpDTO.getGithubName());
        User userByDguEmail = memberRepository.findUserByDguEmail(signUpDTO.getDguEmail());
        if (userByGithubName != null) {
            throw new Exception("GitHub name already exists:  " + signUpDTO.getGithubName());
        }
        if (userByDguEmail != null) {
            throw new Exception("DGU email already exists: " + signUpDTO.getDguEmail());
        }
    }

    @Override
    public Integer checkUserSignUp(String githubId){
        User userByGithubName = memberRepository.findUserByGithubName(githubId);
        if(userByGithubName == null){
            return null;
        }
        else{
            return userByGithubName.getUserId();
        }
    }

    @Override
    public String getUserEmail(int userId) {
        User user = memberRepository.findUserByUserId(userId);
        return user.getDguEmail();
    }
}
