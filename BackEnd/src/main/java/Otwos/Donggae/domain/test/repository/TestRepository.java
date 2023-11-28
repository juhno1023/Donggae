package Otwos.Donggae.domain.test.repository;

import Otwos.Donggae.DAO.Test.Test;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TestRepository extends JpaRepository<Test, Integer> {
    Test findByTestId(int testId);
}
