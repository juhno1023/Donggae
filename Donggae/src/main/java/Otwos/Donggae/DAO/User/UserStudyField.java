package Otwos.Donggae.DAO.User;

import jakarta.persistence.*;
import lombok.Getter;

@Getter
@Entity
@Table(name = "user_study_field")
public class UserStudyField {

    @Id
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Id
    @Column(name = "study_field", length = 20)
    private String studyField;

    // Constructors, getters, and setters
}

