package Otwos.Donggae.domain.team.repository;

import Otwos.Donggae.DAO.Recruit.RecruitPost;
import Otwos.Donggae.DAO.Team.Team;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeamRepository extends JpaRepository<Team, Long> {
    Team findTeamByTeamId(int teamId);

    Team findTeamByTeamName(String teamName);

    Team findTeamByRecruitPostId(RecruitPost recruitPost);

}
