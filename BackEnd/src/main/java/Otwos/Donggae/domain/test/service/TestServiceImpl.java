package Otwos.Donggae.domain.test.service;

import static java.sql.Types.NULL;
import Otwos.Donggae.DAO.Test.AnswerOption;
import Otwos.Donggae.DAO.Test.Test;
import Otwos.Donggae.DAO.Test.TestQuestion;
import Otwos.Donggae.DAO.Test.TestResult;
import Otwos.Donggae.DAO.Test.UserAnswer;
import Otwos.Donggae.DAO.User.User;
import Otwos.Donggae.DTO.test.TestQuestionDTO;
import Otwos.Donggae.DTO.test.TestResultDTO;
import Otwos.Donggae.DTO.test.TestResultDTO.QuestionResultDTO;
import Otwos.Donggae.DTO.test.TestResultDTO.QuestionResultDTO.AnswerResultDTO;
import Otwos.Donggae.DTO.test.UserAnswerDTO;
import Otwos.Donggae.DTO.test.showTestFields.TestDTO;
import Otwos.Donggae.domain.member.repository.MemberRepository;
import Otwos.Donggae.domain.test.repository.AnswerOptionRepository;
import Otwos.Donggae.domain.test.repository.TestQuestionRepository;
import Otwos.Donggae.domain.test.repository.TestRepository;
import Otwos.Donggae.domain.test.repository.TestResultRepository;
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
    @Autowired
    private TestResultRepository testResultRepository;

    @Override
    public List<TestDTO> showTestFields() {
        List<Test> tests = testRepository.findAll();
        List<TestDTO> testDTOS = new ArrayList<>();

        for (Test test : tests) {
            TestDTO testDTO = new TestDTO(test.getTestId(), test.getTestField().label());
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

    @Override
    public TestResultDTO showTestResult(int userId, int testId) {
        int questionNum = 1;
        int correct = 0; //맞은 문제 수
        User user = memberRepository.findUserByUserId(userId);
        Test test = testRepository.findByTestId(testId);
        // 해당 분야의 문제 리스트
        List<TestQuestion> testQuestions = testQuestionRepository.findAllByTestId(test);
        //문제들 DTO
        List<TestResultDTO.QuestionResultDTO> questionResultDTOS = new ArrayList<>();

        for (TestQuestion testQuestion : testQuestions) {
            int answerNum = 1;
            Boolean selectedIsCorrect = Boolean.FALSE; //이 문제 user가 맞았는지?
            //해당 문제의 보기 리스트
            List<AnswerOption> answerOptions = answerOptionRepository.findAllByTestQuestionId(testQuestion);
            //정답들 DTO
            List<TestResultDTO.QuestionResultDTO.AnswerResultDTO> answerResultDTOS = new ArrayList<>();

            for (AnswerOption answerOption : answerOptions) {
                Boolean isSelected = Boolean.FALSE;
                Boolean isCorrectAnswer = Boolean.FALSE;

                //이 문제에 대해 user가 선택한 답
                UserAnswer userAnswer = userAnswerRepository.findByUserIdAndTestQuestionId(user, testQuestion);
                //user가 선택한 답과 현재 보기가 맞으면
                if (userAnswer.getAnswerOptionId() == answerOption) {
                    isSelected = Boolean.TRUE;
                }
                //이 보기가 정답이라면
                if (answerOption.isAnswer() == Boolean.TRUE) {
                    isCorrectAnswer = Boolean.TRUE;
                }
                //user가 선택한 보기가 정답이라면
                if (isSelected == Boolean.TRUE && isCorrectAnswer == Boolean.TRUE) {
                    selectedIsCorrect = Boolean.TRUE;
                    correct++;
                }

                AnswerResultDTO answerResultDTO = new AnswerResultDTO(
                        answerNum, //보기 번호
                        isSelected, //user가 선택한 답인지?
                        isCorrectAnswer, //실제 정답인지
                        answerOption.getContent() //보기 내용
                );
                answerResultDTOS.add(answerResultDTO);
                answerNum++;
            }

            QuestionResultDTO questionResultDTO = new QuestionResultDTO(
                    selectedIsCorrect, //맞았는지?
                    questionNum, //문제 번호
                    testQuestion.getQuestionText(), //문제 내용
                    answerResultDTOS //보기 List
            );
            questionResultDTOS.add(questionResultDTO);
            questionNum++;
        }

        TestResultDTO testResultDTO = new TestResultDTO(
                test.getTestField().label(), //test분야 이름
                correct, //맞은 문제 수
                testQuestionRepository.countAllByTestId(test), //전체 문제 수
                questionResultDTOS //문제 List
        );

        //점수 test_result에 저장
        TestResult testResult = TestResult.builder()
                .userId(user)
                .testId(test)
                .testResult(correct)
                .build();
        testResultRepository.save(testResult);

        List<TestResult> testResults = testResultRepository.findAllByUserId(user);
        int userDevTestScore = 0;
        for (TestResult userTestResult : testResults) {
            userDevTestScore += userTestResult.getTestResult();
        }

        //점수 user에 저장
        User saveScoreUser = User.builder()
                .userId(user.getUserId())
                .githubName(user.getGithubName())
                .intro(user.getIntro())
                .teamExpCount(user.getTeamExpCount())
                .leaderCount(user.getLeaderCount())
                .boj_rank(user.getBoj_rank())
                .devTestScore(userDevTestScore) //test_result에서 불러와서 저장해야함
                .dguEmail(user.getDguEmail())
                .build();
        memberRepository.save(saveScoreUser);

        return testResultDTO;
    }


}
