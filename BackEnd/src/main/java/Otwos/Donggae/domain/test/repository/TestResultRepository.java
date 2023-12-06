package Otwos.Donggae.domain.test.repository;

import Otwos.Donggae.DAO.Test.Identifier.TestResultPK;
import Otwos.Donggae.DAO.Test.TestResult;
import Otwos.Donggae.DAO.User.User;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TestResultRepository extends JpaRepository<TestResult, TestResultPK> {
    List<TestResult> findAllByUserId(User user);
    TestResult findByUserId(User user);
}
