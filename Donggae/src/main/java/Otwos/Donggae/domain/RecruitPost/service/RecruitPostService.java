package Otwos.Donggae.domain.RecruitPost.service;

import Otwos.Donggae.DAO.User.User;
import Otwos.Donggae.DTO.RecruitPost.RecruitPostDTO;
import Otwos.Donggae.DTO.RecruitPost.RecruitPostRequestDTO;
import org.apache.tomcat.util.net.openssl.ciphers.Authentication;

public interface RecruitPostService {
    public void createRecruitPost(RecruitPostRequestDTO recruitPostRequestDTO, int userId);
//    public RecruitPostDTO editRecruitPost(int recruitPostId, RecruitPostRequestDTO recruitPostRequestDTO);
//    public String deleteBoard(int recruitPostId, Authentication authentication);
}
