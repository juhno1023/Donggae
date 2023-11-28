package Otwos.Donggae.DAO.Test;

import Otwos.Donggae.DAO.Test.Identifier.TestResultPK;
import Otwos.Donggae.DAO.User.User;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@IdClass(TestResultPK.class)
@Table(name = "test_result")
public class TestResult {

    // N:1 user
    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User userId;

    // N:1 test
    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "test_id")
    private Test testId;

    @Column(name = "test_result", nullable = false)
    private int testResult;

    @Builder
    public TestResult(User userId, Test testId, int testResult) {
        this.userId = userId;
        this.testId = testId;
        this.testResult = testResult;
    }
}
