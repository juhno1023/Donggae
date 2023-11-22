package Otwos.Donggae.domain.RecruitPost.Repository.info;

import Otwos.Donggae.DAO.Recruit.Identifier.RecruitPersonalityPK;
import Otwos.Donggae.DAO.Recruit.RecruitLanguage;
import Otwos.Donggae.DAO.Recruit.RecruitPersonality;
import Otwos.Donggae.DAO.Recruit.RecruitPost;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface RecruitPersonalityRepository extends JpaRepository<RecruitPersonality, RecruitPersonalityPK> {
    List<RecruitPersonality> findAllByRecruitPostId(RecruitPost recruitPost);
}
