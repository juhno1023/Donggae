package Otwos.Donggae.DTO.RecruitPost.recruitPostInfo;

import Otwos.Donggae.DAO.Recruit.RecruitField;
import Otwos.Donggae.DAO.Recruit.RecruitPost;
import Otwos.Donggae.Global.FieldEnum;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RecruitFieldDTO {

    private int recruitPostId;
    private String field;

    public RecruitField toEntity(RecruitPost recruitPost) {
        return RecruitField.builder()
                .recruitPostId(recruitPost)
                .field(FieldEnum.valueOfLabel(field))
                .build();
    }
}
