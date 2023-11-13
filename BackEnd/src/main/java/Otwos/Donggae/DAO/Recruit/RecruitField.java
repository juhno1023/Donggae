package Otwos.Donggae.DAO.Recruit;

import Otwos.Donggae.DAO.Recruit.Identifier.RecruitFieldPK;
import Otwos.Donggae.Global.FieldEnum;
import Otwos.Donggae.Global.LanguageEnum;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@IdClass(RecruitFieldPK.class)
@Table(name = "recruit_field")
public class RecruitField {

    // N:1 RecruitPost
    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "recruit_post_id")
    private RecruitPost recruitPostId;

    @Id
    @Column(name = "field", length = 20, nullable = false)
    @Enumerated(EnumType.STRING)
    private FieldEnum field;

    @Builder
    public RecruitField(RecruitPost recruitPostId, FieldEnum field) {
        this.recruitPostId = recruitPostId;
        this.field = field;
    }
}
