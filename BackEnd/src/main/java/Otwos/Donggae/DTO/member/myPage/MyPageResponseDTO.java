package Otwos.Donggae.DTO.member.myPage;

import Otwos.Donggae.DTO.member.userinfo.response.UserInterestFieldResponse;
import Otwos.Donggae.DTO.member.userinfo.response.UserLanguageResponse;
import Otwos.Donggae.DTO.member.userinfo.response.UserPersonalityResponse;
import Otwos.Donggae.DTO.member.userinfo.response.UserStudyFieldResponse;
import Otwos.Donggae.Global.Rank.BaekjoonRank;
import Otwos.Donggae.Global.Rank.DonggaeRank;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class MyPageResponseDTO {
    private String githubName;
    private String selfIntro;
    private BaekjoonRank bojRank;
    private String dguEmail;
    private String userRank;
    private int teamExpCount;
    private int leaderCount;
    private int devTestScore;
    private List<UserLanguageResponse> userLanguageDTOS;
    private List<UserInterestFieldResponse> userInterestFieldDTOS;
    private List<UserPersonalityResponse> userPersonalityDTOS;
    private List<UserStudyFieldResponse> userStudyFieldDTOS;

}
