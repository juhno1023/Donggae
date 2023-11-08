package Otwos.Donggae.DTO.application;

import Otwos.Donggae.DAO.Application;
import Otwos.Donggae.DAO.Recruit.RecruitPost;
import Otwos.Donggae.DAO.User.User;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ApplyDTO {
    private int applicationId;
    private String selfIntro;
    private String content;
    private int userId;
    private int recruitPostId;

    public Application toEntity(User user, RecruitPost recruitPost) {
        return Application.builder()
                .applicationId(applicationId)
                .selfIntro(selfIntro)
                .content(content)
                .userId(user)
                .recruitPostId(recruitPost)
                .build();
    }
}
