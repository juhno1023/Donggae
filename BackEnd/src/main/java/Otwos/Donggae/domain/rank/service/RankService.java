package Otwos.Donggae.domain.rank.service;

import Otwos.Donggae.DTO.member.donggaeRank.UserRankInfoDTO;

import java.util.List;

public interface RankService {
    public int calAllUserRankScore();

    public void reallocRank(int userCount);

    public List<UserRankInfoDTO> getRankList();
}
