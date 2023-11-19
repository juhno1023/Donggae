package Otwos.Donggae.domain.rank.service;

public interface RankService {
    public int calAllUserRankScore();

    public void reallocRank(int userCount);
}
