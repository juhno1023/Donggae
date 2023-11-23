package Otwos.Donggae.domain.test.service;

import Otwos.Donggae.DAO.Test.Test;
import Otwos.Donggae.DTO.test.showTestFields.TestDTO;
import Otwos.Donggae.domain.test.repository.TestRepository;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TestServiceImpl implements TestService{
    @Autowired
    private TestRepository testRepository;

    @Override
    public List<TestDTO> showTestFields() {
        List<Test> tests = testRepository.findAll();
        List<TestDTO> testDTOS = new ArrayList<>();

        for (Test test : tests) {
            TestDTO testDTO = new TestDTO(test.getTestId(), test.getTestField());
            testDTOS.add(testDTO);
        }

        return testDTOS;
    }
}
