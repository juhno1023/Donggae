package Otwos.Donggae.DTO.member.previewInfo;

import Otwos.Donggae.DTO.member.userinfo.response.UserInterestFieldResponse;
import Otwos.Donggae.DTO.member.userinfo.response.UserLanguageResponse;
import Otwos.Donggae.DTO.member.userinfo.response.UserPersonalityResponse;
import Otwos.Donggae.DTO.member.userinfo.response.UserStudyFieldResponse;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PreviewUserInfoDTO {
    //private int userId;
    private String githubName;
    private String selfIntro;
    private String bojRank;
    private String dguEmail;
    private String userRank;
    private String userProfile;
    private List<UserLanguageResponse> userLanguageDTOS;
    private List<UserInterestFieldResponse> userInterestFieldDTOS;
    private List<UserPersonalityResponse> userPersonalityDTOS;
    private List<UserStudyFieldResponse> userStudyFieldDTOS;
}
