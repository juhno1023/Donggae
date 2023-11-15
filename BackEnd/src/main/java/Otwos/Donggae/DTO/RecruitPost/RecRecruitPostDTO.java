package Otwos.Donggae.DTO.RecruitPost;

import Otwos.Donggae.DAO.Recruit.RecruitLanguage;
import Otwos.Donggae.Global.MajorLectureEnum;
import Otwos.Donggae.Global.Rank.BaekjoonRank;
import Otwos.Donggae.Global.Rank.DonggaeRank;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
public class RecRecruitPostDTO {

    private int recruitPostId;

    private MajorLectureEnum majorLectureName;

    private String title;

    private List<RecruitLanguage> recruitLanguages;

    private DonggaeRank donggaeRank;

    private BaekjoonRank bojRank;

    private String userName;

    private Date createDate;

}
