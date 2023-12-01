package Otwos.Donggae.Global.Rank;

import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public enum DonggaeRank {
    DDONGGAE("똥개"),
    DONGDONGGAE("동동개"),
    SILVERDONGGAE("은동개"),
    GOLDDONGGAE("황금동개"),
    DIADONGGAE("다이아동개");

    private final String label;

    DonggaeRank(String label) {
        this.label = label;
    }

    public String label(){ return label; }

    private static final Map<String, DonggaeRank> BY_LABEL =
            Stream.of(values()).collect(Collectors.toMap(DonggaeRank::label, e -> e));

    public static DonggaeRank valueOfLabel(String label) {
        return BY_LABEL.get(label);
    }
}
