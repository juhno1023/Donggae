package Otwos.Donggae.domain.RecruitPost.Repository.info;

import Otwos.Donggae.DAO.Recruit.RecruitLanguage;
import Otwos.Donggae.DAO.Recruit.RecruitPersonality;
import Otwos.Donggae.DAO.Recruit.RecruitPost;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RecruitPersonalityRepository extends JpaRepository<RecruitPersonality, Long> {
}
