package Otwos.Donggae.domain.test.repository;

import Otwos.Donggae.DAO.Test.TestQuestion;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TestQuestionRepository extends JpaRepository<TestQuestion, Long> {
}
