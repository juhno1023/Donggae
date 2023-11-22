package Otwos.Donggae.domain.RecruitPost.Repository.info;

import Otwos.Donggae.DAO.Recruit.Identifier.RecruitFieldPK;
import Otwos.Donggae.DAO.Recruit.RecruitField;
import Otwos.Donggae.DAO.Recruit.RecruitLanguage;
import Otwos.Donggae.DAO.Recruit.RecruitPost;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface RecruitFieldRepository extends JpaRepository<RecruitField, RecruitFieldPK> {

    List<RecruitField> findAllByRecruitPostId(RecruitPost recruitPost);
}
