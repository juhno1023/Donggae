package Otwos.Donggae.DTO.RecruitPost.recruitPostInfo.response;

import Otwos.Donggae.Global.LanguageEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RecruitLanguageResponse {
    private LanguageEnum language;
}
