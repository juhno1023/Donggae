package Otwos.Donggae.DAO.Test;

import Otwos.Donggae.DAO.User;
import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Getter
@Table(name = "test_result")
public class TestResult {

    @Id
    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "userId")
    private User user;

    @Id
    @ManyToOne
    @JoinColumn(name = "test_id", referencedColumnName = "testId")
    private Test test;

    @Column(name = "test_result")
    private int testResult;

}
