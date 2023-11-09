package Otwos.Donggae.DTO.RecruitPost;

import Otwos.Donggae.DAO.Application;
import Otwos.Donggae.DAO.Recruit.RecruitField;
import Otwos.Donggae.DAO.Recruit.RecruitLanguage;
import Otwos.Donggae.DAO.Recruit.RecruitPersonality;
import Otwos.Donggae.DAO.Recruit.RecruitPost;
import Otwos.Donggae.DAO.User.User;
import Otwos.Donggae.Global.MajorLectureEnum;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class RecruitPostResponseDTO {
    private int recruitPostId;

    private User userId;

    private String title;

    private String content;

    private MajorLectureEnum majorLectureName;

    private Date createdDate;

    private List<RecruitField> recruitFields;

    private List<RecruitLanguage> recruitLanguages;

    private List<RecruitPersonality> recruitPersonalities;

    private List<Application> applications;

    public RecruitPostResponseDTO(RecruitPost recruitPost){
        recruitPostId=recruitPost.getRecruitPostId();
        userId=recruitPost.getUserId();
        title=recruitPost.getTitle();
        content=recruitPost.getContent();
        majorLectureName=recruitPost.getMajorLectureName();
        createdDate=recruitPost.getCreatedDate();
        //모집 분야 리스트
        recruitFields = recruitPost.getRecruitFields().stream()
                .map(recruitField -> new recruitFieldDTO(recruitField.getField()))
                .collect(Collectors.toList());
        //모집 언어 리스트
        recruitLanguages = recruitPost.getRecruitLanguages().stream()
                .map(language -> new languageDTO(language.getLanguage()))
                .collect(Collectors.toList());
        //모집 성향 리스트
        recruitPersonalities = recruitPost.getRecruitPersonalities().stream()
                .map(personality -> new personalityDTO(personality.getPersonality()))
                .collect(Collectors.toList());
        //지원서 리스트
        applications = recruitPost.getApplications().stream()
                .map(application -> new applicationDTO(application.getApplicationId()))
                .collect(Collectors.toList());
    }
}
