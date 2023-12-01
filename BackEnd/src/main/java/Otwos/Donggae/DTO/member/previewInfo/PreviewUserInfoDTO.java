package Otwos.Donggae.DTO.member.previewInfo;

import Otwos.Donggae.DTO.member.userinfo.response.UserInterestFieldResponse;
import Otwos.Donggae.DTO.member.userinfo.response.UserLanguageResponse;
import Otwos.Donggae.DTO.member.userinfo.response.UserPersonalityResponse;
import Otwos.Donggae.DTO.member.userinfo.response.UserStudyFieldResponse;
import Otwos.Donggae.Global.Rank.BaekjoonRank;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PreviewUserInfoDTO {
    //private int userId;
    private String githubName;
    private String selfIntro;
    private BaekjoonRank bojRank;
    private String dguEmail;
    private String userRank;
    private List<UserLanguageResponse> userLanguageDTOS;
    private List<UserInterestFieldResponse> userInterestFieldDTOS;
    private List<UserPersonalityResponse> userPersonalityDTOS;
    private List<UserStudyFieldResponse> userStudyFieldDTOS;
}
