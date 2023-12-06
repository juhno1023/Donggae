package Otwos.Donggae.domain.test.repository;

import Otwos.Donggae.DAO.Test.Test;
import Otwos.Donggae.DAO.Test.TestQuestion;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TestQuestionRepository extends JpaRepository<TestQuestion, Integer> {
    List<TestQuestion> findAllByTestId(Test testId);

    TestQuestion findTestQuestionByTestQuestionId(int testQuestionId);

    //문제 몇개인지
    int countAllByTestId(Test testId);
}
