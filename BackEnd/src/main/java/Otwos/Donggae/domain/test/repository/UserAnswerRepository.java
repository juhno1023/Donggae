package Otwos.Donggae.domain.test.repository;

import Otwos.Donggae.DAO.Test.Identifier.TestResultPK;
import Otwos.Donggae.DAO.Test.TestQuestion;
import Otwos.Donggae.DAO.Test.TestResult;
import Otwos.Donggae.DAO.Test.UserAnswer;
import Otwos.Donggae.DAO.User.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

public interface UserAnswerRepository extends JpaRepository<UserAnswer, Integer> {
    UserAnswer findByUserIdAndTestQuestionId(User user, TestQuestion testQuestion);

    void deleteByTestQuestionIdAndUserId(TestQuestion testQuestionId, User userId);
}
