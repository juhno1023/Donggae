package Otwos.Donggae.DAO.Test;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "answer_option")
public class AnswerOption {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private int id;

    /*
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "test_id", referencedColumnName = "testId")
    private Test testId;
    */

    // N:1 test_question
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "test_question_id")
    private TestQuestion testQuestionId;

    @Column(name = "content", length = 100, nullable = false)
    private String content;

    @Column(name = "is_answer")
    private boolean isAnswer;
}