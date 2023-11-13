package Otwos.Donggae.domain.team.repository;

import Otwos.Donggae.DAO.Team.Team;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeamRepository extends JpaRepository<Team, Long> {
    public Team findTeamByTeamId(int teamId);

    public Team findTeamByTeamName(String teamName);
}
