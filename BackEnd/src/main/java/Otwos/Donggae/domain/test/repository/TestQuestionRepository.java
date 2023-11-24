package Otwos.Donggae.domain.test.repository;

import Otwos.Donggae.DAO.Test.Test;
import Otwos.Donggae.DAO.Test.TestQuestion;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TestQuestionRepository extends JpaRepository<TestQuestion, Long> {
    List<TestQuestion> findAllByTestId(Test testId);
}
