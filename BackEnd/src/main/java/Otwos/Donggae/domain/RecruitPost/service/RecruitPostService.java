package Otwos.Donggae.domain.RecruitPost.service;

import Otwos.Donggae.DTO.RecruitPost.RecruitPostDTO;
import Otwos.Donggae.DTO.RecruitPost.RecruitPostRequestDTO;
import org.apache.tomcat.util.net.openssl.ciphers.Authentication;

public interface RecruitPostService {
    public RecruitPostDTO createBoard(RecruitPostRequestDTO recruitPostRequestDTO, Authentication authentication);
    public RecruitPostDTO editRecruitPost(int recruitPostId, RecruitPostRequestDTO recruitPostRequestDTO);
    public String deleteBoard(int recruitPostId, Authentication authentication);
}
