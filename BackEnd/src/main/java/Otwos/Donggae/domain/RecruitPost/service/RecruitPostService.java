package Otwos.Donggae.domain.RecruitPost.service;

import Otwos.Donggae.DAO.User.User;
import Otwos.Donggae.DTO.RecruitPost.RecruitPostDTO;
import Otwos.Donggae.DTO.RecruitPost.RecruitPostRequestDTO;
import Otwos.Donggae.DTO.RecruitPost.RecruitPostResponseDTO;
import org.apache.tomcat.util.net.openssl.ciphers.Authentication;

public interface RecruitPostService {
    public void createRecruitPostAndTeam(RecruitPostRequestDTO recruitPostRequestDTO, int userId);
//    public RecruitPostDTO editRecruitPost(int recruitPostId, RecruitPostRequestDTO recruitPostRequestDTO);
    public String deleteRecruitPost(int recruitPostId, int userId);

//    public RecruitPostResponseDTO getRecruitPost (int recruitPostId);
    public void editRecruitPost(int recruitPostId, RecruitPostRequestDTO recruitPostRequestDTO, int userId);
}
