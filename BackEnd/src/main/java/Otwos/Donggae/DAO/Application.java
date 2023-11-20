package Otwos.Donggae.DAO;
import Otwos.Donggae.DAO.Recruit.RecruitPost;
import Otwos.Donggae.DAO.User.User;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "application")
public class Application {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "application_id", nullable = false)
    private int applicationId;

    @Column(name = "self_intro")
    private String selfIntro;

    @Column(name = "content", length = 1000)
    private String content;

    //팀장 (글쓴이) 아이디
    // N:1 user
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User userId;

    // N:1 RecruitPost
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "recruit_post_id")
    private RecruitPost recruitPostId;

    @Builder
    public Application(int applicationId, String selfIntro,
                       String content, User userId,
                       RecruitPost recruitPostId) {
        this.applicationId = applicationId;
        this.selfIntro = selfIntro;
        this.content = content;
        this.userId = userId;
        this.recruitPostId = recruitPostId;
    }

}
