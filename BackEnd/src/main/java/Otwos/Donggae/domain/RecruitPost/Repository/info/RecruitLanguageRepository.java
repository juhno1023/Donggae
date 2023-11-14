package Otwos.Donggae.domain.RecruitPost.Repository.info;

import Otwos.Donggae.DAO.Recruit.RecruitLanguage;
import Otwos.Donggae.DAO.Recruit.RecruitPost;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RecruitLanguageRepository extends JpaRepository<RecruitLanguage, Long> {
    List<RecruitLanguage> findRecruitLanguageByRecruitPostId(RecruitPost recruitPost);


}
