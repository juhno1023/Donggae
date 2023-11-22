package Otwos.Donggae.DTO.RecruitPost;

import Otwos.Donggae.DAO.Application;
import Otwos.Donggae.DAO.Recruit.RecruitField;
import Otwos.Donggae.DAO.Recruit.RecruitLanguage;
import Otwos.Donggae.DAO.Recruit.RecruitPersonality;
import Otwos.Donggae.DAO.Recruit.RecruitPost;
import Otwos.Donggae.DAO.User.User;
import Otwos.Donggae.DTO.RecruitPost.recruitPostInfo.RecruitFieldDTO;
import Otwos.Donggae.DTO.RecruitPost.recruitPostInfo.response.RecruitFieldResponse;
import Otwos.Donggae.DTO.RecruitPost.recruitPostInfo.response.RecruitLanguageResponse;
import Otwos.Donggae.DTO.RecruitPost.recruitPostInfo.response.RecruitPersonalityResponse;
import Otwos.Donggae.DTO.member.userinfo.response.UserInterestFieldResponse;
import Otwos.Donggae.DTO.member.userinfo.response.UserLanguageResponse;
import Otwos.Donggae.DTO.member.userinfo.response.UserPersonalityResponse;
import Otwos.Donggae.DTO.member.userinfo.response.UserStudyFieldResponse;
import Otwos.Donggae.Global.MajorLectureEnum;
import Otwos.Donggae.Global.Rank.BaekjoonRank;
import Otwos.Donggae.Global.Rank.DonggaeRank;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class RecruitPostDetailResponseDTO {
    private int recruitPostId;

    private String title;

    private String content;

    private MajorLectureEnum majorLectureName;

    private Timestamp createdDate;

    private List<RecruitFieldResponse> recruitFields;

    private List<RecruitLanguageResponse> recruitLanguages;

    private List<RecruitPersonalityResponse> recruitPersonalities;
    @JsonIgnore
    private User userId;
    private String githubName;
    private String dguEmail;
    private String selfIntro;
    private BaekjoonRank bojRank;
    private DonggaeRank userRank;
    private List<UserLanguageResponse> userLanguageDTOS;
    private List<UserInterestFieldResponse> userInterestFieldDTOS;
    private List<UserPersonalityResponse> userPersonalityDTOS;
}
