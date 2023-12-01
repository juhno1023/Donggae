package Otwos.Donggae.DTO.RecruitPost;

import Otwos.Donggae.DAO.User.User;
import Otwos.Donggae.DTO.RecruitPost.recruitPostInfo.response.RecruitFieldResponse;
import Otwos.Donggae.DTO.RecruitPost.recruitPostInfo.response.RecruitLanguageResponse;
import Otwos.Donggae.DTO.RecruitPost.recruitPostInfo.response.RecruitPersonalityResponse;
import Otwos.Donggae.DTO.member.userinfo.response.UserInterestFieldResponse;
import Otwos.Donggae.DTO.member.userinfo.response.UserLanguageResponse;
import Otwos.Donggae.DTO.member.userinfo.response.UserPersonalityResponse;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.util.List;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class RecruitPostDetailResponseDTO {
    private int recruitPostId;

    private String title;

    private String content;

    private String majorLectureName;

    private Timestamp createdDate;

    private List<RecruitFieldResponse> recruitFields;

    private List<RecruitLanguageResponse> recruitLanguages;

    private List<RecruitPersonalityResponse> recruitPersonalities;
    @JsonIgnore
    private User userId;
    private String githubName;
    private String dguEmail;
    private String selfIntro;
    private String bojRank;
    private String userRank;
    private List<UserLanguageResponse> userLanguageDTOS;
    private List<UserInterestFieldResponse> userInterestFieldDTOS;
    private List<UserPersonalityResponse> userPersonalityDTOS;
}
