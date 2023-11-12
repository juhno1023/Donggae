package Otwos.Donggae.domain.RecruitPost.Repository.info;

import Otwos.Donggae.DAO.Recruit.RecruitField;
import Otwos.Donggae.DAO.Recruit.RecruitLanguage;
import Otwos.Donggae.DAO.Recruit.RecruitPost;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RecruitFieldRepository extends JpaRepository<RecruitField, Long> {
}
