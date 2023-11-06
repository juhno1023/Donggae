package Otwos.Donggae.DAO;
import Otwos.Donggae.DAO.Recruit.RecruitPost;
import Otwos.Donggae.DAO.User.User;
import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Getter
@Table(name = "application")
public class Application {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "application_id")
    private int applicationId;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "recruit_post_id")
    private RecruitPost recruitPost;

    @Column(name = "self_intro", length = 255)
    private String selfIntro;

    @Column(name = "content", length = 1000)
    private String content;

}
