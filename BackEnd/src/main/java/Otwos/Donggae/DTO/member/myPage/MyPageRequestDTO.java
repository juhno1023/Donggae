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
