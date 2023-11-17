package Otwos.Donggae.DAO.Recruit;
import Otwos.Donggae.DAO.Application;
import Otwos.Donggae.DAO.User.User;
import Otwos.Donggae.Global.MajorLectureEnum;
import Otwos.Donggae.Global.Rank.BaekjoonRank;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;


@Setter
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

    @Column(name = "major_lecture_name", length = 200)
    @Enumerated(EnumType.STRING)
    private MajorLectureEnum majorLectureName;

    @Column(name = "created_date")
    private Timestamp createdDate;

    @Column(name = "is_complete", nullable = false, columnDefinition = "boolean default false")
    private Boolean isComplete;

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

    @Builder
    public RecruitPost(int recruitPostId, User userId, String title, String content, MajorLectureEnum majorLectureName, Timestamp createdDate, List<RecruitField> recruitFields, List<RecruitLanguage> recruitLanguages, List<RecruitPersonality> recruitPersonalities, List<Application> applications) {
        this.recruitPostId = recruitPostId;
        this.userId = userId;
        this.title = title;
        this.content = content;
        this.majorLectureName = majorLectureName;
        this.createdDate = createdDate;
        this.recruitFields = recruitFields;
        this.recruitLanguages = recruitLanguages;
        this.recruitPersonalities = recruitPersonalities;
        this.applications = applications;
        this.isComplete = false;
    }
}

