package Otwos.Donggae.DAO.Recruit;

import Otwos.Donggae.DAO.Recruit.Identifier.RecruitPersonalityPK;
import Otwos.Donggae.Global.FieldEnum;
import Otwos.Donggae.Global.LanguageEnum;
import Otwos.Donggae.Global.PersonalityEnum;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
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
    @Column(name = "personality", length = 200, nullable = false)
    @Enumerated(EnumType.STRING)
    private PersonalityEnum personality;

    @Builder
    public RecruitPersonality(RecruitPost recruitPostId, PersonalityEnum personality) {
        this.recruitPostId = recruitPostId;
        this.personality = personality;
    }

    public RecruitPersonality(RecruitPost recruitPostId, String personality) {
        this.recruitPostId = recruitPostId;
        this.personality = PersonalityEnum.valueOf(personality); // Enum으로 변환
    }
}
