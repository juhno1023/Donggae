package Otwos.Donggae.DTO.member.myPage;

import Otwos.Donggae.DTO.member.userinfo.response.UserInterestFieldResponse;
import Otwos.Donggae.DTO.member.userinfo.response.UserLanguageResponse;
import Otwos.Donggae.DTO.member.userinfo.response.UserPersonalityResponse;
import Otwos.Donggae.DTO.member.userinfo.response.UserStudyFieldResponse;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class MyPageResponseDTO {
    private String githubName;
    private String selfIntro;
    private String bojRank;
    private String dguEmail;
    private String userRank;
    private int teamExpCount;
    private int leaderCount;
    private int devTestScore;
    private String userProfile;
    private List<UserLanguageResponse> userLanguageDTOS;
    private List<UserInterestFieldResponse> userInterestFieldDTOS;
    private List<UserPersonalityResponse> userPersonalityDTOS;
    private List<UserStudyFieldResponse> userStudyFieldDTOS;

}
