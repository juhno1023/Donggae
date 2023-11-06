package Otwos.Donggae.DAO.Test;
import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Getter
@Table(name = "answer_option")
public class AnswerOption {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @ManyToOne
    @JoinColumn(name = "test_id", referencedColumnName = "testId")
    private Test test;

    @ManyToOne
    @JoinColumn(name = "test_question_id", referencedColumnName = "testQuestionId")
    private TestQuestion testQuestion;

    @Column(name = "content", length = 100, nullable = false)
    private String content;



}