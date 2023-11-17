package Otwos.Donggae.domain.member.repository;

import Otwos.Donggae.DAO.User.User;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<User, Long> {
    User findUserByGithubName(String githubId);

    User findUserByDguEmail(String dguEmail);

    User findUserByUserId(int userId);

    @EntityGraph(attributePaths = {"userLanguages", "userInterestFields", "userPersonalities"})
    User findUserWithDetailsByUserId(int userId);
}
