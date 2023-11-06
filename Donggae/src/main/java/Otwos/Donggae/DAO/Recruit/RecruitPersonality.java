package Otwos.Donggae.DAO.Recruit;

import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Getter
@Table(name = "recruit_personality")
public class RecruitPersonality {

    @Id
    @ManyToOne
    @JoinColumn(name = "recruit_post_id")
    private RecruitPost recruitPost;

    @Id
    @Column(name = "personality", length = 20)
    private String personality;

    // Constructors, getters, and setters
}
