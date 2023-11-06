package Otwos.Donggae.DAO.Recruit;

import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Getter
@Table(name = "recruit_field")
public class RecruitField {

    @Id
    @ManyToOne
    @JoinColumn(name = "recruit_post_id")
    private RecruitPost recruitPost;

    @Id
    @Column(name = "field", length = 20)
    private String field;

    // Constructors, getters, and setters
}
