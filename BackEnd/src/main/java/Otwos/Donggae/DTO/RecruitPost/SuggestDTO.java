package Otwos.Donggae.DTO.RecruitPost;

import Otwos.Donggae.DAO.Recruit.RecruitPost;
import Otwos.Donggae.DAO.Suggest;
import Otwos.Donggae.DAO.User.User;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SuggestDTO {

    private int suggest_id;
    private User userId;
    private RecruitPost recruitPostId;

    public Suggest toEntity(User user, RecruitPost recruitPost) {
        return Suggest.builder()
                .suggest_id(suggest_id)
                .userId(user)
                .recruitPostId(recruitPost)
                .build();
    }

}
