package Otwos.Donggae.DAO.User;

import jakarta.persistence.*;
import lombok.Getter;

@Getter
@Entity
@Table(name = "user_rank")
public class UserRank {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private int id;

    // 1:1 user 단방향
    @OneToOne
    @JoinColumn(name = "user_id")
    private User userId;

    @Column(name = "score")
    private Integer score;

    @Column(name = "rank", length = 10, columnDefinition = "VARCHAR(10) DEFAULT '똥개'")
    private String rank;
}
