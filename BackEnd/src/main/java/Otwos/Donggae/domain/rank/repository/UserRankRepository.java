package Otwos.Donggae.domain.rank.repository;

import Otwos.Donggae.DAO.User.User;
import Otwos.Donggae.DAO.User.UserRank;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRankRepository extends JpaRepository<UserRank, Long> {
    UserRank findUserRankByUserId(User user);
}
