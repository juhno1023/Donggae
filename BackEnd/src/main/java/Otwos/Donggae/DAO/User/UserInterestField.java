package Otwos.Donggae.DAO.User;

import Otwos.Donggae.DAO.User.Identifier.UserInterestFieldPK;
import Otwos.Donggae.Global.FieldEnum;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@IdClass(UserInterestFieldPK.class)
@Table(name = "user_interest_field")
public class UserInterestField {

    // N:1 user
    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User userId;

    @Id
    @Column(name = "interest_field", length = 200, nullable = false)
    @Enumerated(EnumType.STRING)
    private FieldEnum interestField; // 관심분야

    @Builder
    public UserInterestField(User userId, FieldEnum interestField) {
        this.userId = userId;
        this.interestField = interestField;
    }
}
