package Otwos.Donggae.DAO.Recruit;
import Otwos.Donggae.DAO.Application;
import Otwos.Donggae.DAO.User.User;
import Otwos.Donggae.Global.MajorLectureEnum;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;

import java.sql.Date;
import java.util.List;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Table(name = "recruit_post")
public class RecruitPost {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "recruit_post_id", nullable = false)
    private int recruitPostId;

    // N:1 user
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User userId;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "content", length = 1000, nullable = false)
    private String content;

    @Column(name = "major_lecture_name", length = 20)
    @Enumerated(EnumType.STRING)
    private MajorLectureEnum majorLectureName;

    @Column(name = "created_date")
    private Date createdDate;

    // 1:N recruitField
    @OneToMany(mappedBy = "recruitPostId")
    private List<RecruitField> recruitFields;

    // 1:N recruitLanguage
    @OneToMany(mappedBy = "recruitPostId")
    private List<RecruitLanguage> recruitLanguages;

    // 1:N recruitPersonality
    @OneToMany(mappedBy = "recruitPostId")
    private List<RecruitPersonality> recruitPersonalities;

    // 1:N Application
    @OneToMany(mappedBy = "recruitPostId")
    private List<Application> applications;
}

