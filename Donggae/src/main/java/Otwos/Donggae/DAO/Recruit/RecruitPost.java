package Otwos.Donggae.DAO.Recruit;
import Otwos.Donggae.DAO.Application;
import Otwos.Donggae.DAO.Team.Team;
import Otwos.Donggae.DAO.User.User;
import jakarta.persistence.*;
import lombok.Getter;

import java.sql.Date;
import java.util.List;

@Entity
@Table(name = "recruit_post")
@Getter
public class RecruitPost {

    @Id
    @Column(name = "recruit_post_id")
    private int recruitPostId;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "title")
    private String title;

    @Column(name = "content")
    private String content;

    @Column(name = "major_lecture_name")
    private String majorLectureName;

    @Column(name = "created_date")
    private Date createdDate;

    @OneToMany(mappedBy = "recruitPost")
    private List<Team> teams;

    @OneToMany(mappedBy = "recruitPost")
    private List<Application> applications;

    // Getters and setters
}

