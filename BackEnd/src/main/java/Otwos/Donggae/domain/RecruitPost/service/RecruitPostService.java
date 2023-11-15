package Otwos.Donggae.domain.RecruitPost.service;

import Otwos.Donggae.DTO.RecruitPost.RecRecruitPostDTO;
import Otwos.Donggae.DTO.RecruitPost.RecruitPostRequestDTO;

import java.util.List;

public interface RecruitPostService {
    public void createRecruitPostAndTeam(RecruitPostRequestDTO recruitPostRequestDTO, int userId);
//    public RecruitPostDTO editRecruitPost(int recruitPostId, RecruitPostRequestDTO recruitPostRequestDTO);
//    public String deleteBoard(int recruitPostId, Authentication authentication);
}
