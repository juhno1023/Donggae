package Otwos.Donggae.DAO.Test;

import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Getter
@Table(name = "test")
public class Test {
    @Id
    @Column(name = "test_id")
    private int testId;

    @Column(name = "test_field", length = 20, nullable = false)
    private String testField;
}
