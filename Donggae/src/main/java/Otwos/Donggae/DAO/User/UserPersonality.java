package Otwos.Donggae.DAO.User;

import Otwos.Donggae.DAO.User.Identifier.UserPersonalityPK;
import Otwos.Donggae.Global.PersonalityEnum;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@IdClass(UserPersonalityPK.class)
@Table(name = "user_personality")
public class UserPersonality {

    // N:1 user
    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User userId;

    @Id
    @Column(name = "personality", length = 20, nullable = false)
    @Enumerated(EnumType.STRING)
    private PersonalityEnum personality;
}

