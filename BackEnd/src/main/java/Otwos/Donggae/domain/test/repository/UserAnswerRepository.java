package Otwos.Donggae.domain.test.repository;

import Otwos.Donggae.DAO.Test.Identifier.TestResultPK;
import Otwos.Donggae.DAO.Test.TestResult;
import Otwos.Donggae.DAO.Test.UserAnswer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserAnswerRepository extends JpaRepository<UserAnswer, Integer> {
}
