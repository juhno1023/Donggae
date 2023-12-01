package Otwos.Donggae.DTO.RecruitPost;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class RecruitPostRequestDTO {

    private String title;

    private String content;

    private String majorLectureName;

    private List<String> recruitFields;

    private List<String> recruitLanguages;

    private List<String> recruitPersonalities;

    private String teamName;

}
