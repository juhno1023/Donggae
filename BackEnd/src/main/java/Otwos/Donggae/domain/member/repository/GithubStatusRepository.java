package Otwos.Donggae.domain.member.repository;

import Otwos.Donggae.DAO.GithubStatus;
import Otwos.Donggae.DAO.User.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GithubStatusRepository extends JpaRepository<GithubStatus, Long> {

    GithubStatus findByUserId(User userId);
}
