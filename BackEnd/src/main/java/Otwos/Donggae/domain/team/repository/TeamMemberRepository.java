package Otwos.Donggae.domain.team.repository;

import Otwos.Donggae.DAO.Team.Identifier.TeamMemberPK;
import Otwos.Donggae.DAO.Team.Team;
import Otwos.Donggae.DAO.Team.TeamMember;
import Otwos.Donggae.DAO.User.User;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeamMemberRepository extends JpaRepository<TeamMember, TeamMemberPK> {
    TeamMember findTeamMemberByTeamIdAndUserId(Team teamId, User userId);

    void deleteTeamMemberByTeamIdAndUserId(Team teamId, User userId);

    List<TeamMember> findTeamMembersByUserId(User userId);
}
