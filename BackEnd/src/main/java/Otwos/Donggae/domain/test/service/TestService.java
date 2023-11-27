package Otwos.Donggae.domain.test.service;

import Otwos.Donggae.DTO.test.TestQuestionDTO;
import Otwos.Donggae.DTO.test.TestResultDTO;
import Otwos.Donggae.DTO.test.UserAnswerDTO;
import Otwos.Donggae.DTO.test.showTestFields.TestDTO;

import java.util.ArrayList;
import java.util.List;

public interface TestService {
    List<TestDTO> showTestFields();

    List<TestQuestionDTO> showTestQuestions(int testId);

    void saveUserAnswer(int userId, ArrayList<UserAnswerDTO> userAnswerDTOs);
    TestResultDTO showTestResult(int userId, int testId);
}
