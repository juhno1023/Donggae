package Otwos.Donggae.DTO.test;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserAnswerDTO {

    private int testQuestionId;
    private int answerOptionId;
}
