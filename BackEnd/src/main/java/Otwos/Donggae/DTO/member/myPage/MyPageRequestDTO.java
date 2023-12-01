package Otwos.Donggae.DTO.member.myPage;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MyPageRequestDTO {
    private String selfIntro;
    private List<String> userLanguages;
    private List<String> userInterestFields;
    private List<String> userPersonalities;
}
