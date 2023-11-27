package Otwos.Donggae.domain.member.service;


import Otwos.Donggae.DTO.member.baekjoonRank.UserBaekjoonRankRequestDTO;
import org.springframework.stereotype.Service;

@Service
public interface SolvedacService {

    void getMyBaekjoonRank(UserBaekjoonRankRequestDTO userBaekjoonRankRequestDTO, int userId);

}