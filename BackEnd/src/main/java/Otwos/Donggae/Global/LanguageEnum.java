package Otwos.Donggae.Global;

import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public enum LanguageEnum {
    JAVASCRIPT("JavaScript"),
    TYPESCRIPT("TypeScript"),
    REACT("React"),
    VUE("Vue"),
    SVELTE("Svelte"),
    NEXTJS("Nextjs"),
    NODEJS("Nodejs"),
    JAVA("Java"),
    SPRING("Spring"),
    GO("Go"),
    NESTJS("Nestjs"),
    KOTLIN("Kotlin"),
    EXPRESS("Express"),
    MYSQL("MySQL"),
    MONGODB("MongoDB"),
    PYTHON("Python"),
    DJANGO("Django"),
    PHP("php"),
    GRAPHQL("GraphQL"),
    FIREBASE("Firebase"),
    FLUTTER("Flutter"),
    SWIFT("Swift"),
    REACTNATIVE("ReactNative"),
    UNITY("Unity"),
    AWS("AWS"),
    KUBERNETES("kubernetes"),
    DOCKER("Docker"),
    GIT("Git"),
    FIGMA("Figma"),
    ZEPLIN("Zeplin"),
    JEST("Jest");

    private final String label;

    LanguageEnum(String label) {
        this.label = label;
    }

    public String label() {
        return label;
    }

    private static final Map<String, LanguageEnum> BY_LABEL =
            Stream.of(values()).collect(Collectors.toMap(LanguageEnum::label, e -> e));

    public static LanguageEnum valueOfLabel(String lable) {
        return BY_LABEL.get(lable);
    }
}
