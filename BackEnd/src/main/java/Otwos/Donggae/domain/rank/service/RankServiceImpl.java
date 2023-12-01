package Otwos.Donggae.domain.rank.service;

import Otwos.Donggae.DAO.GithubStatus;
import Otwos.Donggae.DAO.User.User;
import Otwos.Donggae.DAO.User.UserRank;
import Otwos.Donggae.DTO.member.donggaeRank.UserRankInfoDTO;
import Otwos.Donggae.Global.Rank.BaekjoonRank;
import Otwos.Donggae.Global.Rank.DonggaeRank;
import Otwos.Donggae.domain.member.repository.GithubStatusRepository;
import Otwos.Donggae.domain.member.repository.MemberRepository;
import Otwos.Donggae.domain.rank.repository.UserRankRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RankServiceImpl implements RankService{

    private final UserRankRepository userRankRepository;

    private final MemberRepository memberRepository;

    private final GithubStatusRepository githubStatusRepository;

    @Override
    public int calAllUserRankScore(){
        // 모든 user의 랭크 점수 갱신
        List<User> userList = memberRepository.findAll();
        for (User user : userList) {
            calUserRankScore(user.getUserId());
        }
        return userList.size();
    }

    private void calUserRankScore(int usrId) {
        // 한 user의 랭크 점수 갱신
        User user = memberRepository.findUserByUserId(usrId);
        UserRank userRank = userRankRepository.findUserRankByUserId(user);
        GithubStatus userGithubStatus = githubStatusRepository.findByUserId(user);

        float new_score = 0f;

        if(userGithubStatus != null){
            new_score += userGithubStatus.getCommitNum() * 0.5; //0.5점
            new_score += userGithubStatus.getIssueNum() * 5; //5점
            new_score += userGithubStatus.getPrNum() * 5; // 5점
            new_score += userGithubStatus.getStarNum() * 3; //3점
        }

        new_score += user.getBoj_rank().getScore(); // 백준 랭크

        new_score += user.getLeaderCount(); //1점
        new_score += user.getTeamExpCount(); //1점

        UserRank updatedRank = new UserRank(userRank.getId(), userRank.getUserId(), (int)new_score, userRank.getRankName());
        userRankRepository.save(updatedRank);
    }

    @Override
    public void reallocRank(int userCount) {
        // 모든 사용자의 현재 랭크 점수를 가져온 후, 점수에 따라 정렬
        List<UserRank> userRanks = userRankRepository.findAllByOrderByScoreDesc();

        // 각 랭크별 할당 인원 계산
        int rankDiaDonggaeCount = (int) (userCount * 0.1); // 다이아동개
        int rankGoldDonggaeCount = (int) (userCount * 0.1); // 황금동개
        int rankEundonggaeCount = (int) (userCount * 0.2); // 은동개
        int rankDongdonggaeCount = (int) (userCount * 0.4); // 동동개
        // 똥개는 남은 인원 모두

        // 랭크 재할당
        for (int i = 0; i < userRanks.size(); i++) {
            UserRank userRank = userRanks.get(i);

            DonggaeRank updatedRank;
            if (i < rankDiaDonggaeCount) {
                updatedRank = DonggaeRank.DIADONGGAE;
            } else if (i < rankDiaDonggaeCount + rankGoldDonggaeCount) {
                updatedRank = DonggaeRank.GOLDDONGGAE;
            } else if (i < rankDiaDonggaeCount + rankGoldDonggaeCount + rankEundonggaeCount) {
                updatedRank = DonggaeRank.SILVERDONGGAE;
            } else if (i < rankDiaDonggaeCount + rankGoldDonggaeCount + rankEundonggaeCount + rankDongdonggaeCount) {
                updatedRank = DonggaeRank.DONGDONGGAE;
            } else {
                updatedRank = DonggaeRank.DDONGGAE;
            }
            UserRank updatedUserRank = UserRank.builder().id(userRank.getId()).rankName(updatedRank).score(userRank.getScore()).user(userRank.getUserId()).build();
            userRankRepository.save(updatedUserRank);
        }
    }

    @Override
    public List<UserRankInfoDTO> getRankList() {
        // 전체 랭크 리스트 반환
        List<UserRank> userRankList = userRankRepository.findAllByOrderByScoreDesc();
        List<UserRankInfoDTO> userRankInfoDTOList = new ArrayList<>();
        int index = 1;

        for (UserRank userRank : userRankList) {
            User user = userRank.getUserId();
            DonggaeRank rankName = userRank.getRankName();
            String githubName = user.getGithubName();
            List<String> userInterestFields = user.getUserInterestFields().stream().map(userInterestField -> userInterestField.getInterestField().name()).toList();
            BaekjoonRank bojRank = user.getBoj_rank();
            int score = userRank.getScore();

            UserRankInfoDTO userRankInfoDTO = new UserRankInfoDTO(index, rankName.label(), githubName, userInterestFields, score, bojRank.label());

            userRankInfoDTOList.add(userRankInfoDTO);

            index++;
        }

        return userRankInfoDTOList;
    }
}
