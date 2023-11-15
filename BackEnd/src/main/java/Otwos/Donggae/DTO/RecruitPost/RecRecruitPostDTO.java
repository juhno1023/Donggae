package Otwos.Donggae.DTO.RecruitPost;

import Otwos.Donggae.DAO.Recruit.RecruitLanguage;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
public class RecRecruitPostDTO {

    private int recruitPostId;

    private String majorLectureName;

    private String title;

    private List<RecruitLanguage> recruitLanguages;

    private String donggaeRank;

    private String bojRank;

    private String userName;

    private Date createDate;

}
