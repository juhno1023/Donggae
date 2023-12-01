package Otwos.Donggae.DTO.RecruitPost;

import Otwos.Donggae.DAO.Application;
import Otwos.Donggae.DAO.Recruit.RecruitField;
import Otwos.Donggae.DAO.Recruit.RecruitLanguage;
import Otwos.Donggae.DAO.Recruit.RecruitPersonality;
import Otwos.Donggae.DAO.Recruit.RecruitPost;
import Otwos.Donggae.DAO.User.User;
import Otwos.Donggae.Global.MajorLectureEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import java.sql.Timestamp;
import java.util.List;

@Data
@AllArgsConstructor
public class RecruitPostDTO {

    private int recruitPostId;

    private User userId;

    private String title;

    private String content;

    private String majorLectureName;

    private Timestamp createdDate;
    private Boolean isComplete;

    private List<RecruitField> recruitFields;

    private List<RecruitLanguage> recruitLanguages;

    private List<RecruitPersonality> recruitPersonalities;

    private List<Application> applications;

    public RecruitPost toEntity() {
        return RecruitPost.builder()
                .recruitPostId(recruitPostId)
                .userId(userId)
                .title(title)
                .content(content)
                .majorLectureName(MajorLectureEnum.valueOfLabel(majorLectureName))
                .createdDate(createdDate)
                .isComplete(isComplete)
                .build();
    }
}
