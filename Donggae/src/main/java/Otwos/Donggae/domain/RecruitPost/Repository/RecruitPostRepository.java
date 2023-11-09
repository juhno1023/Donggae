package Otwos.Donggae.domain.RecruitPost.Repository;

import Otwos.Donggae.DAO.Recruit.RecruitPost;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RecruitPostRepository extends JpaRepository<RecruitPost, Integer> {
    RecruitPost findRecruitPostByRecruitPostId(int id);
    RecruitPost save(RecruitPost recruitPost);

}
