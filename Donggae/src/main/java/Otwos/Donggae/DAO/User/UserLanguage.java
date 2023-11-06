package Otwos.Donggae.DAO.User;

import Otwos.Donggae.DAO.User.Identifier.UserLanguagePK;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@IdClass(UserLanguagePK.class)
@Table(name = "user_language")
public class UserLanguage {

    // N:1 user
    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User userId;

    @Id
    @Column(name = "language")
    private String language; // 기술스택(언어)
}
