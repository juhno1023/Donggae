package Otwos.Donggae.domain.member.service;

import Otwos.Donggae.DTO.member.myPage.MyPageRequestDTO;
import Otwos.Donggae.DTO.member.myPage.MyPageResponseDTO;
import org.springframework.stereotype.Service;

@Service
public interface MyPageService{

    MyPageResponseDTO showMyInfo(int userId);

    void editMyInfo(MyPageRequestDTO myPageRequestDTO, int userId);

}
