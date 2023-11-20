package Otwos.Donggae.domain.RecruitPost.Repository.info;

import Otwos.Donggae.DAO.Recruit.Identifier.RecruitPersonalityPK;
import Otwos.Donggae.DAO.Recruit.RecruitPersonality;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RecruitPersonalityRepository extends JpaRepository<RecruitPersonality, RecruitPersonalityPK> {
}
