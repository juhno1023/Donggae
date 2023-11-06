package Otwos.Donggae.DAO.User;

import jakarta.persistence.*;
import lombok.Getter;

@Getter
@Entity
@Table(name = "user_interest_field")
public class UserInterestField {

    @Id
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "interest_field")
    private String interests; // 관심분야
}
