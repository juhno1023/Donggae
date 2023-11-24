package Otwos.Donggae.DTO.test;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class TestQuestionDTO {
    private int questionNum;
    private String questionText;
    private List<AnswerOptionDTO> answerOptions;

    @Data
    @AllArgsConstructor
    @Builder
    public static class AnswerOptionDTO{
        private int answerNum;
        private String answerText;
    }
}
