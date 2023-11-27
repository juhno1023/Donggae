package Otwos.Donggae.DTO.test;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserAnswerDTO {

    private int testQuestionId;
    private int answerOptionId;
}
