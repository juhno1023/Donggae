package Otwos.Donggae.domain.test.repository;

import Otwos.Donggae.DAO.Test.AnswerOption;
import Otwos.Donggae.DAO.Test.TestQuestion;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AnswerOptionRepository extends JpaRepository<AnswerOption, Integer> {
    List<AnswerOption> findAllByTestQuestionId(TestQuestion testQuestion);
}
