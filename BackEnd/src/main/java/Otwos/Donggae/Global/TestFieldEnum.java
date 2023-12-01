package Otwos.Donggae.Global;

import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public enum TestFieldEnum {
    DATASTRUCTURE("DataStructure"),
    DATABASE("Database"),
    OPERATINGSYSTEM("OperatingSystem");

    private final String label;

    TestFieldEnum(String label) {
        this.label = label;
    }

    public String label() {
        return label;
    }

    private static final Map<String, TestFieldEnum> BY_LABEL =
            Stream.of(values()).collect(Collectors.toMap(TestFieldEnum::label, e -> e));

    public static TestFieldEnum valueOfLabel(String label) {
        return BY_LABEL.get(label);
    }
}
