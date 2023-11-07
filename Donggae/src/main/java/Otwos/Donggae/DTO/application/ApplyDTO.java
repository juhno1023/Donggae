package Otwos.Donggae.DTO.application;

import Otwos.Donggae.DAO.Application;
import Otwos.Donggae.DAO.User.User;

public class ApplyDTO {
    private int applicationId;
    private String selfIntro;
    private String content;

    public Application toEntity() {
        return Application.builder()
                .applicationId(applicationId)
                .selfIntro(selfIntro)
                .content(content)
                .build();
    }
}
