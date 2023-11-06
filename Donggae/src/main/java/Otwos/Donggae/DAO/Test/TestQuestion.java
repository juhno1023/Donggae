package Otwos.Donggae.DAO.Test;

import jakarta.persistence.*;
import lombok.Getter;
@Entity
@Getter
@Table(name = "test_question")
public class TestQuestion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "test_question_id")
    private int testQuestionId;

    @ManyToOne
    @JoinColumn(name = "test_id", referencedColumnName = "testId")
    private Test test;

    @Column(name = "question_text", length = 100, nullable = false)
    private String questionText;

    @ManyToOne
    @JoinColumn(name = "answer_id", nullable = false, referencedColumnName = "id")
    private AnswerOption answerOption;

}
