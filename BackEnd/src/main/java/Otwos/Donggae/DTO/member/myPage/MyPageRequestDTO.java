package Otwos.Donggae.DTO.member.myPage;

import Otwos.Donggae.DAO.User.UserInterestField;
import Otwos.Donggae.DAO.User.UserLanguage;
import Otwos.Donggae.DAO.User.UserPersonality;
import Otwos.Donggae.DAO.User.UserStudyField;
import Otwos.Donggae.DTO.member.userinfo.UserLanguageDTO;
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
public class MyPageRequestDTO {
    private String selfIntro;
    private String boj_Id;
    private List<UserLanguage> userLanguageDTOS;
    private List<UserInterestField> userInterestFieldDTOS;
    private List<UserPersonality> userPersonalityDTOS;
    private List<UserStudyField> userStudyFieldDTOS;

}
