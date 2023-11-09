package Otwos.Donggae.domain.member.service;

import Otwos.Donggae.DTO.member.register.SignUpDTO;

public interface MemberService {
    public void register(SignUpDTO signUpDTO);

    public Integer checkUserSignUp(String githubId);
}
