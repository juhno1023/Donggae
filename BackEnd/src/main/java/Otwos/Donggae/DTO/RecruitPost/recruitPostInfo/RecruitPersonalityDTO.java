package Otwos.Donggae.DTO.RecruitPost.recruitPostInfo;

import Otwos.Donggae.DAO.Recruit.RecruitPersonality;
import Otwos.Donggae.DAO.Recruit.RecruitPost;
import Otwos.Donggae.DAO.User.UserPersonality;
import Otwos.Donggae.Global.PersonalityEnum;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RecruitPersonalityDTO {
    private int recruitPostId;
    private PersonalityEnum personality;

    public RecruitPersonality toEntity(RecruitPost recruitPost) {
        return RecruitPersonality.builder()
                .recruitPostId(recruitPost)
                .personality(personality)
                .build();
    }
}
