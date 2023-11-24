package Otwos.Donggae.DAO.Test;

import Otwos.Donggae.DAO.User.User;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "user_answer")
public class UserAnswer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private int id;

    // N:1 User
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User userId;

    // N:1 testQuestion
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "test_question_id")
    private TestQuestion testQuestionId;

    // N:1 answerOption
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "answer_option_id")
    private AnswerOption answerOptionId;
}
