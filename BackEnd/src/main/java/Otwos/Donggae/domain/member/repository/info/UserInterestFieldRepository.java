package Otwos.Donggae.domain.member.repository.info;

import Otwos.Donggae.DAO.User.Identifier.UserInterestFieldPK;
import Otwos.Donggae.DAO.User.User;
import Otwos.Donggae.DAO.User.UserInterestField;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserInterestFieldRepository extends JpaRepository<UserInterestField, UserInterestFieldPK> {
    List<UserInterestField> findAllByUserId(User user);
}
