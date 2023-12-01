package Otwos.Donggae.Global;

import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

//필요 없어짐
public enum StudyFieldEnum {
    ALGORITHM("Algorithm"),
    SERVER("Server"),
    DATABASE("Database"),
    OPERATINGSYSTEM("OperatingSystem"),
    WEB("Web");

    private final String label;

    StudyFieldEnum(String label) {
        this.label = label;
    }

    public String label() {
        return label;
    }

    private static final Map<String, StudyFieldEnum> BY_LABEL =
            Stream.of(values()).collect(Collectors.toMap(StudyFieldEnum::label, e -> e));

    public static StudyFieldEnum valueOfLabel(String label) {
        return BY_LABEL.get(label);
    }
}
