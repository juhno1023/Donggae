package Otwos.Donggae.domain.member.repository.info;

import Otwos.Donggae.DAO.User.Identifier.UserStudyFieldPK;
import Otwos.Donggae.DAO.User.User;
import Otwos.Donggae.DAO.User.UserStudyField;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserStudyFieldRepository extends JpaRepository<UserStudyField, UserStudyFieldPK> {
    List<UserStudyField> findUserStudyFieldsByUserId(User user);
}
