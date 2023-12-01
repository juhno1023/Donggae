package Otwos.Donggae.Global;

import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public enum FieldEnum {
    BACKEND("BackEnd"),
    FRONTEND("FrontEnd"),
    IOS("iOS"),
    ANDROID("Android"),
    AI("AI"),
    GAME("Game"),
    UIUX("UIUX");

    private final String label;

    FieldEnum(String label) {
        this.label = label;
    }

    public String label() {
        return label;
    }

    private static final Map<String, FieldEnum> BY_LABEL =
            Stream.of(values()).collect(Collectors.toMap(FieldEnum::label, e -> e));

    public static FieldEnum valueOfLabel(String label) {
        return BY_LABEL.get(label);
    }
}
