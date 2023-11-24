package Otwos.Donggae.domain.test.service;

import static java.sql.Types.NULL;
import Otwos.Donggae.DAO.Test.AnswerOption;
import Otwos.Donggae.DAO.Test.Test;
import Otwos.Donggae.DAO.Test.TestQuestion;
import Otwos.Donggae.DAO.Test.UserAnswer;
import Otwos.Donggae.DAO.User.User;
import Otwos.Donggae.DTO.test.TestQuestionDTO;
import Otwos.Donggae.DTO.test.UserAnswerDTO;
import Otwos.Donggae.DTO.test.showTestFields.TestDTO;
import Otwos.Donggae.domain.member.repository.MemberRepository;
import Otwos.Donggae.domain.test.repository.AnswerOptionRepository;
import Otwos.Donggae.domain.test.repository.TestQuestionRepository;
import Otwos.Donggae.domain.test.repository.TestRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import Otwos.Donggae.domain.test.repository.UserAnswerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TestServiceImpl implements TestService{
    @Autowired
    private TestRepository testRepository;

    @Autowired
    private TestQuestionRepository testQuestionRepository;

    @Autowired
    private AnswerOptionRepository answerOptionRepository;

    @Autowired
    private UserAnswerRepository userAnswerRepository;

    @Autowired
    private MemberRepository memberRepository;

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

    @Override
    public List<TestQuestionDTO> showTestQuestions(int testId) {
        int questionNum = 1;

        Optional<Test> test = testRepository.findById(testId);
        List<TestQuestionDTO> testQuestionDTOS = new ArrayList<>();
        if(test.isPresent()){
            List<TestQuestion> testQuestions = testQuestionRepository.findAllByTestId(test.get());

            for (TestQuestion testQuestion : testQuestions) {

                List<AnswerOption> answerOptions = answerOptionRepository.findAllByTestQuestionId(testQuestion);
                List<TestQuestionDTO.AnswerOptionDTO> answerOptionDTOS = new ArrayList<>();

                int answerNum = 1;
                for (AnswerOption answerOption : answerOptions) {

                    TestQuestionDTO.AnswerOptionDTO answerOptionDTO
                        = TestQuestionDTO.AnswerOptionDTO.builder()
                                                        .answerId(answerOption.getId())
                                                        .answerNum(answerNum)
                                                        .answerText(answerOption.getContent())
                                                        .build();
                    answerOptionDTOS.add(answerOptionDTO);
                    answerNum++;
                }
                TestQuestionDTO testQuestionDTO = new TestQuestionDTO(testQuestion.getTestQuestionId(),questionNum, testQuestion.getQuestionText(), answerOptionDTOS);
                testQuestionDTOS.add(testQuestionDTO);
                questionNum++;
            }

        }
        return testQuestionDTOS;
    }

    @Override
    public void saveUserAnswer(int userId, ArrayList<UserAnswerDTO> userAnswerDTOs) {
        for (UserAnswerDTO userAnswerDTO : userAnswerDTOs) {
            User user = memberRepository.findUserByUserId(userId);
            Optional<TestQuestion> testQuestion = testQuestionRepository.findById(userAnswerDTO.getTestQuestionId());
            Optional<AnswerOption> answerOption = answerOptionRepository.findById(userAnswerDTO.getAnswerOptionId());

            if(testQuestion.isPresent() && answerOption.isPresent()){
                UserAnswer userAnswer = UserAnswer.builder()
                        .id(NULL)
                        .userId(user)
                        .testQuestionId(testQuestion.get())
                        .answerOptionId(answerOption.get())
                        .build();

                userAnswerRepository.save(userAnswer);
            }
        }
    }


}
