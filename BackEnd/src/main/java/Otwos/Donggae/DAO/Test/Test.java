package Otwos.Donggae.DAO.Test;

import Otwos.Donggae.Global.TestFieldEnum;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "test")
public class Test {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "test_id", nullable = false)
    private int testId;

    @Column(name = "test_field", length = 20, nullable = false)
    @Enumerated(EnumType.STRING)
    private TestFieldEnum testField;
}
