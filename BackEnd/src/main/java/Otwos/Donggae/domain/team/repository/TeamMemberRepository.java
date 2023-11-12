package Otwos.Donggae.domain.team.repository;

import Otwos.Donggae.DAO.Team.Identifier.TeamMemberPK;
import Otwos.Donggae.DAO.Team.TeamMember;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeamMemberRepository extends JpaRepository<TeamMember, TeamMemberPK> {
}
