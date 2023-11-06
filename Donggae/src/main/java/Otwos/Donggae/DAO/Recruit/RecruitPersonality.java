package Otwos.Donggae.DAO.Recruit;

import Otwos.Donggae.DAO.Recruit.Identifier.RecruitPersonalityPK;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@IdClass(RecruitPersonalityPK.class)
@Table(name = "recruit_personality")
public class RecruitPersonality {

    // N:1 RecruitPost
    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "recruit_post_id")
    private RecruitPost recruitPostId;

    @Id
    @Column(name = "personality", length = 20, nullable = false)
    private String personality;
}
