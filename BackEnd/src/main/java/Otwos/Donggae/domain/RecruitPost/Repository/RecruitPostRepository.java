package Otwos.Donggae.domain.RecruitPost.Repository;

import Otwos.Donggae.DAO.Recruit.RecruitPost;
import Otwos.Donggae.DAO.User.User;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RecruitPostRepository extends JpaRepository<RecruitPost, Integer> {
    RecruitPost findRecruitPostByRecruitPostId(int id);

    RecruitPost findRecruitPostByTitle(String title);

    RecruitPost save(RecruitPost recruitPost);

    @EntityGraph(attributePaths = {"recruitFields", "recruitLanguages", "recruitPersonalities"}, type = EntityGraph.EntityGraphType.FETCH)
    List<RecruitPost> findAllByIsCompleteAndUserIdNot(Boolean isComplete, User user);


    //강의 모집 글 최신2개 (is_complete == true)
    List<RecruitPost> findTop2ByIsCompleteFalseAndMajorLectureNameIsNotNullOrderByCreatedDateDesc(Pageable pageable);

    //일반 모집 글 최신2개 (is_complete == true)
    List<RecruitPost> findTop2ByIsCompleteFalseAndMajorLectureNameIsNullOrderByCreatedDateDesc(Pageable pageable);
}
