package Otwos.Donggae.DTO.RecruitPost.recruitPostInfo;

import Otwos.Donggae.DAO.Recruit.RecruitLanguage;
import Otwos.Donggae.DAO.Recruit.RecruitPost;
import Otwos.Donggae.Global.LanguageEnum;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RecruitLanguageDTO {

    private RecruitPost recruitPostId;
    private LanguageEnum language;

    public RecruitLanguage toEntity() {
        return RecruitLanguage.builder()
                .recruitPostId(recruitPostId)
                .language(language)
                .build();
    }
}