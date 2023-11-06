package Otwos.Donggae.DAO.Recruit;

import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Getter
@Table(name = "recruit_language")
public class RecruitLanguage {

    @Id
    @ManyToOne
    @JoinColumn(name = "recruit_post_id")
    private RecruitPost recruitPost;

    @Id
    @Column(name = "language", length = 20)
    private String language;

    // Constructors, getters, and setters
}