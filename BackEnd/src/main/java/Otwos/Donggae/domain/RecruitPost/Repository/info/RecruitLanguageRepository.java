package Otwos.Donggae.domain.RecruitPost.Repository.info;

import Otwos.Donggae.DAO.Recruit.Identifier.RecruitLanguagePK;
import Otwos.Donggae.DAO.Recruit.RecruitLanguage;
import Otwos.Donggae.DAO.Recruit.RecruitPost;
import Otwos.Donggae.DAO.User.User;
import Otwos.Donggae.Global.LanguageEnum;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RecruitLanguageRepository extends JpaRepository<RecruitLanguage, RecruitLanguagePK> {
    List<RecruitLanguage> findAllByRecruitPostId(RecruitPost recruitPost);

    List<RecruitLanguage> findAllByLanguage(LanguageEnum language);

    void deleteAllByRecruitPostId(RecruitPost recruitPost);
}
