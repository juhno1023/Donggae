package Otwos.Donggae.DTO.RecruitPost.recruitPostInfo;

import Otwos.Donggae.DAO.Recruit.RecruitField;
import Otwos.Donggae.DAO.Recruit.RecruitPost;
import Otwos.Donggae.DAO.User.User;
import Otwos.Donggae.DAO.User.UserInterestField;
import Otwos.Donggae.Global.FieldEnum;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RecruitFieldDTO {

    private int recruitPostId;
    private FieldEnum field;

    public RecruitField toEntity(RecruitPost recruitPost) {
        return RecruitField.builder()
                .recruitPostId(recruitPost)
                .field(field)
                .build();
    }
}
