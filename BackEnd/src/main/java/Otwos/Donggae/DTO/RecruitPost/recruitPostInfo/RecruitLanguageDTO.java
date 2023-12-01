package Otwos.Donggae.DTO.RecruitPost.recruitPostInfo;

import Otwos.Donggae.DAO.Recruit.RecruitLanguage;
import Otwos.Donggae.DAO.Recruit.RecruitPost;
import Otwos.Donggae.Global.LanguageEnum;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RecruitLanguageDTO {

    private int recruitPostId;
    private String language;
    public RecruitLanguage toEntity(RecruitPost recruitPost) {
        return RecruitLanguage.builder()
                .recruitPostId(recruitPost)
                .language(LanguageEnum.valueOfLabel(language))
                .build();
    }
}