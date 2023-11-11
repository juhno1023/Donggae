package Otwos.Donggae.domain.member.repository.info;

import Otwos.Donggae.DAO.User.User;
import Otwos.Donggae.DAO.User.UserLanguage;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserLanguageRepository extends JpaRepository<UserLanguage, Long> {
    List<UserLanguage> findUserLanguagesByUserId(User user);
}
