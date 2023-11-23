package Otwos.Donggae.DTO.test.showTestFields;

import Otwos.Donggae.Global.TestFieldEnum;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TestDTO {
    private int id;
    private TestFieldEnum testField;
}
