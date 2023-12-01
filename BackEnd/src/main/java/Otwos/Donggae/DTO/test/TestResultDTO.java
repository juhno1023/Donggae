package Otwos.Donggae.DTO.test;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TestResultDTO {
    private String testField; //test 분야 이름
    private int correct; //맞은 문제 수
    private int total; //전체 문제 수
    private List<QuestionResultDTO> questionResults;

    @Data
    @AllArgsConstructor
    public static class QuestionResultDTO{
        private boolean isCorrect; //맞았는지?
        private int questionNum; //문제 번호
        private String questionText; //문제 내용
        private List<AnswerResultDTO> answerResults;

        @Data
        @AllArgsConstructor
        public static class AnswerResultDTO {
            private int answerNum; //보기 번호
            private Boolean isSelected; //user가 선택한 답인지
            private Boolean isCorrect; //실제 정답인지
            private String answerText; //보기 내용
        }
    }
}
