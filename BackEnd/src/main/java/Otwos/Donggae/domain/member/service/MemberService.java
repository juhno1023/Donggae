package Otwos.Donggae.domain.member.service;

import Otwos.Donggae.DTO.member.register.SignUpDTO;
import Otwos.Donggae.DTO.member.register.ValidGithubIdRequest;

public interface MemberService {
    public void register(SignUpDTO signUpDTO);

    public Integer checkUserSignUp(String githubId);

    public String getUserEmail(int userId);
    public void validGithubName(ValidGithubIdRequest request);
}
