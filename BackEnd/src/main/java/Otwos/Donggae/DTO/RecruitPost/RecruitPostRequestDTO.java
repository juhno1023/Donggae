package Otwos.Donggae.DTO.RecruitPost;

import Otwos.Donggae.DAO.Application;
import Otwos.Donggae.DAO.Recruit.RecruitField;
import Otwos.Donggae.DAO.Recruit.RecruitLanguage;
import Otwos.Donggae.DAO.Recruit.RecruitPersonality;
import Otwos.Donggae.DAO.User.User;
import Otwos.Donggae.Global.MajorLectureEnum;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.util.List;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class RecruitPostRequestDTO {

    private String title;

    private String content;

    private MajorLectureEnum majorLectureName;

    private List<RecruitField> recruitFields;

    private List<RecruitLanguage> recruitLanguages;

    private List<RecruitPersonality> recruitPersonalities;

}
