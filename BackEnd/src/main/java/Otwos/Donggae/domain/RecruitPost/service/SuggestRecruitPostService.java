package Otwos.Donggae.domain.RecruitPost.service;

import Otwos.Donggae.DAO.Recruit.RecruitPost;
import Otwos.Donggae.DAO.Suggest;
import Otwos.Donggae.DTO.RecruitPost.SuggestRequestDTO;
import Otwos.Donggae.DTO.team.showMyTeam.TeamByMember;
import java.util.List;

public interface SuggestRecruitPostService {
    List<TeamByMember> showSuggestRecruitPosts(int userId);
    public void suggestRecruitPost(SuggestRequestDTO suggestRequestDTO);
}
