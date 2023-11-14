package Otwos.Donggae.DAO.Recruit;

import Otwos.Donggae.DAO.Recruit.Identifier.RecruitLanguagePK;
import Otwos.Donggae.DAO.User.User;
import Otwos.Donggae.Global.LanguageEnum;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
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
    @Column(name = "language", length = 200, nullable = false)
    @Enumerated(EnumType.STRING)
    private LanguageEnum language;

    @Builder
    public RecruitLanguage(RecruitPost recruitPostId, LanguageEnum language) {
        this.recruitPostId = recruitPostId;
        this.language = language;
    }
    public RecruitLanguage toEntity(RecruitPost recruitPostId, LanguageEnum language) {
        return RecruitLanguage.builder()
                .recruitPostId(recruitPostId)
                .language(language)
                .build();
    }

    public LanguageEnum getLanguage() {
        return this.language;
    }
}