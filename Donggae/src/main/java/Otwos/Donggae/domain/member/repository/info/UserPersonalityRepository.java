package Otwos.Donggae.domain.member.repository.info;

import Otwos.Donggae.DAO.User.User;
import Otwos.Donggae.DAO.User.UserPersonality;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserPersonalityRepository extends JpaRepository<UserPersonality, Long> {
    List<UserPersonality> findUserPersonalitiesByUserId(User user);
}