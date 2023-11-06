package Otwos.Donggae.DAO.User;

import jakarta.persistence.*;
import lombok.Getter;

@Getter
@Entity
@Table(name = "user_rank")
public class UserRank {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "score")
    private Integer score;

    @Column(name = "rank", length = 10, columnDefinition = "VARCHAR(10) DEFAULT '똥개'")
    private String rank;

    // Constructors, getters, and setters
}
