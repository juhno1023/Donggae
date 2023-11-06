package Otwos.Donggae.DAO.Recruit;

import Otwos.Donggae.DAO.Recruit.Identifier.RecruitLanguagePK;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@IdClass(RecruitLanguagePK.class)
@Table(name = "recruit_language")
public class RecruitLanguage {

    // N:1 RecruitPost
    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "recruit_post_id")
    private RecruitPost recruitPostId;

    @Id
    @Column(name = "language", length = 20, nullable = false)
    private String language;
}