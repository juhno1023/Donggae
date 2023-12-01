package Otwos.Donggae.Global;

import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public enum MajorLectureEnum {
    ADVENTUREDESIGN("어드벤처디자인"),
    INTROSWENGINEERING("소프트웨어공학개론"),
    COMALGORITHM("컴퓨터알고리즘과실습"),
    OPENSWPROJECT("공개SW프로젝트"),
    WEBPROGRAMMING("웹프로그래밍"),
    OODESIGNANDPATTERN("객체지향설계와패턴"),
    COMPREHENSIVECOM1("컴퓨터공학종합설계1"),
    COMPREHENSIVECOM2("컴퓨터공학종합설계2"),
    HUMANCOMINTERACTIONSYS("인간컴퓨터상호작용시스템"),
    DATAANALYSIS("데이터분석및실습");

    private final String label;

    MajorLectureEnum(String label) {
        this.label = label;
    }

    public String label(){
        return label;
    }

    private static final Map<String, MajorLectureEnum> BY_LABEL =
            Stream.of(values()).collect(Collectors.toMap(MajorLectureEnum::label, e -> e));

    public static MajorLectureEnum valueOfLabel(String label) {
        return BY_LABEL.get(label);
    }
}
