package Otwos.Donggae.domain.test.service;

import Otwos.Donggae.DTO.test.TestQuestionDTO;
import Otwos.Donggae.DTO.test.showTestFields.TestDTO;
import java.util.List;

public interface TestService {
    List<TestDTO> showTestFields();

    List<TestQuestionDTO> showTestQuestions(int testId);
}
