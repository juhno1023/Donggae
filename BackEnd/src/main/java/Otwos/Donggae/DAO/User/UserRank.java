package Otwos.Donggae.DAO.User;

import Otwos.Donggae.Global.Rank.DonggaeRank;
import jakarta.persistence.*;
import lombok.Builder;
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

    @Column(name = "rank_name", length = 10, columnDefinition = "VARCHAR(10) DEFAULT '똥개'")
    @Enumerated(EnumType.STRING)
    private DonggaeRank rankName;

    @Builder
    public UserRank(int id, User user, int score, DonggaeRank rankName) {
        this.id = id;
        this.userId = user;
        this.score = score;
        this.rankName = rankName;
    }
}
