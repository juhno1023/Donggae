package Otwos.Donggae.DAO.Recruit;

import Otwos.Donggae.DAO.Recruit.Identifier.RecruitFieldPK;
import Otwos.Donggae.Global.FieldEnum;
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
    @Column(name = "field", length = 200, nullable = false)
    @Enumerated(EnumType.STRING)
    private FieldEnum field;

    @Builder
    public RecruitField(RecruitPost recruitPostId, FieldEnum field) {
        this.recruitPostId = recruitPostId;
        this.field = field;
    }

    public RecruitField(RecruitPost recruitPostId, String field) {
        this.recruitPostId = recruitPostId;
        this.field = FieldEnum.valueOfLabel(field); // Enum으로 변환
    }
}
