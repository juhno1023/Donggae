package Otwos.Donggae.domain.member.service;

import Otwos.Donggae.DTO.RecruitPost.RecRecruitPostDTO;
import Otwos.Donggae.DTO.team.RecMemberDTO;

import java.util.List;

public interface RecMemberService {
        List<RecMemberDTO> recommendMember(int userId);
}
