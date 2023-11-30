package Otwos.Donggae.DAO.User;

import Otwos.Donggae.DAO.User.Identifier.UserLanguagePK;
import Otwos.Donggae.Global.LanguageEnum;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Entity
@Setter
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
    @Column(name = "language", length = 200, nullable = false)
    @Enumerated(EnumType.STRING)
    private LanguageEnum language; // 기술스택(언어)

    @Builder
    public UserLanguage(User userId, LanguageEnum language) {
        this.userId = userId;
        this.language = language;
    }
}
