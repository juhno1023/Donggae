package Otwos.Donggae.DAO.User;

import Otwos.Donggae.DAO.User.Identifier.UserStudyFieldPK;
import Otwos.Donggae.Global.StudyFieldEnum;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@IdClass(UserStudyFieldPK.class)
@Table(name = "user_study_field")
public class UserStudyField {

    // N:1 user
    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User userId;

    @Id
    @Column(name = "study_field", length = 20, nullable = false)
    @Enumerated(EnumType.STRING)
    private StudyFieldEnum studyField;
}

