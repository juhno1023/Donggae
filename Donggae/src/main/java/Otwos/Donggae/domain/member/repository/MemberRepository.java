package Otwos.Donggae.domain.member.repository;

import Otwos.Donggae.DAO.User.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<User, Long> {
    User findUserByGithubName(String githubId);

    User findUserByDguEmail(String dguEmail);
}
