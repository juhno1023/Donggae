package Otwos.Donggae.Global;

import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public enum PersonalityEnum {
    LOGICAL("논리적인"),
    STRATEGIC("계획적인"),
    METICULOUS("꼼꼼한"),
    PROMPT("신속한"),
    CHEERFUL("쾌활한"),
    CREATIVE("창의적인"),
    DILIGENT("성실한"),
    GOAL_ORIENTED("목표지향적"),
    PERSISTENT("끈기있는"),
    LEADER("리더"),
    FOLLOWER("팔로워"),
    COMMUNICATOR("커뮤니케이터"),
    PERFECTIONIST("완벽주의자"),
    ADVENTURER("모험가"),
    INVENTOR("발명가"),
    ANALYST("분석가"),
    MEDIATOR("중재자"),
    JACKOFALLTRADES("만능재주꾼");

    private final String label;

    PersonalityEnum(String label) {
        this.label = label;
    }

    public String label() {
        return label;
    }

    private static final Map<String, PersonalityEnum> BY_LABEL =
            Stream.of(values()).collect(Collectors.toMap(PersonalityEnum::label, e -> e));

    public static PersonalityEnum valueOfLabel(String label) {
        return BY_LABEL.get(label);
    }
}
