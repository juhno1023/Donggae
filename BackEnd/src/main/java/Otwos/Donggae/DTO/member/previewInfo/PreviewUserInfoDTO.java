package Otwos.Donggae.DTO.member.previewInfo;

import Otwos.Donggae.DTO.member.userinfo.UserInterestFieldDTO;
import Otwos.Donggae.DTO.member.userinfo.UserLanguageDTO;
import Otwos.Donggae.DTO.member.userinfo.UserPersonalityDTO;
import Otwos.Donggae.DTO.member.userinfo.UserStudyFieldDTO;
import Otwos.Donggae.Global.Rank.BaekjoonRank;
import Otwos.Donggae.Global.Rank.DonggaeRank;
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
    private DonggaeRank userRank;
    private List<UserLanguageDTO> userLanguageDTOS;
    private List<UserInterestFieldDTO> userInterestFieldDTOS;
    private List<UserPersonalityDTO> userPersonalityDTOS;
    private List<UserStudyFieldDTO> userStudyFieldDTOS;
}
