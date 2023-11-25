package Otwos.Donggae.domain.RecruitPost.Repository;

import Otwos.Donggae.DAO.Suggest;
import Otwos.Donggae.DAO.User.User;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SuggestRepository extends JpaRepository<Suggest, Long> {
    List<Suggest> findAllByUserId(User user);
}
