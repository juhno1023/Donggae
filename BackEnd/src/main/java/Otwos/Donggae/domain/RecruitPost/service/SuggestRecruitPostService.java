package Otwos.Donggae.domain.RecruitPost.service;

import Otwos.Donggae.DTO.team.showMyTeam.TeamByMember;
import java.util.List;

public interface SuggestRecruitPostService {
    List<TeamByMember> showSuggestRecruitPosts(int userId);
}
