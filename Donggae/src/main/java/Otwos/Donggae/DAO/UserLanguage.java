package Otwos.Donggae.DAO;

import jakarta.persistence.*;
import lombok.Getter;

@Getter
@Entity
@Table(name = "user_language")
public class UserLanguage {

    @Id
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "language")
    private String language; // 기술스택(언어)
}
