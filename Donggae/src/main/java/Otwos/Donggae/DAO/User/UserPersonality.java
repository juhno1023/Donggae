package Otwos.Donggae.DAO.User;

import jakarta.persistence.*;
import lombok.Getter;

@Getter
@Entity
@Table(name = "user_personality")
public class UserPersonality {

    @Id
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Id
    @Column(name = "personality", length = 20)
    private String personality;

    // Constructors, getters, and setters
}

