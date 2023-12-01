package Otwos.Donggae.Global.Rank;

import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public enum BaekjoonRank {
    UNRATED("Unrated", 0),
    BRONZE_I("Bronze_I", 6),
    BRONZE_II("Bronze_II", 7),
    BRONZE_III("Bronze_III", 8),
    BRONZE_IV("Bronze_IV", 9),
    BRONZE_V("Bronze_V", 10),
    SILVER_I("Silver_I", 11),
    SILVER_II("Silver_II", 12),
    SILVER_III("Silver_III", 13),
    SILVER_IV("Silver_IV", 14),
    SILVER_V("Silver_V", 15),
    GOLD_I("Gold_I", 16),
    GOLD_II("Gold_II", 17),
    GOLD_III("Gold_III", 18),
    GOLD_IV("Gold_IV", 19),
    GOLD_V("Gold_V", 20),
    PLATINUM_I("Platinum_I", 21),
    PLATINUM_II("Platinum_II", 22),
    PLATINUM_III("Platinum_III", 23),
    PLATINUM_IV("Platinum_IV", 24),
    PLATINUM_V("Platinum_V", 25),
    DIAMOND_I("Diamond_I", 26),
    DIAMOND_II("Diamond_II", 27),
    DIAMOND_III("Diamond_III", 28),
    DIAMOND_IV("Diamond_IV", 29),
    DIAMOND_V("Diamond_V", 30),
    RUBY_I("Ruby_I", 31),
    RUBY_II("Ruby_II", 32),
    RUBY_III("Ruby_III", 33),
    RUBY_IV("Ruby_IV", 34),
    RUBY_V("Ruby_V", 35);

    private final String label;
    private final int score;

    BaekjoonRank(String label, int score) {
        this.label = label;
        this.score = score;
    }

    public int getScore() {
        return score;
    }

    public String label() {
        return label;
    }

    private static final Map<String, BaekjoonRank> BY_LABEL =
            Stream.of(values()).collect(Collectors.toMap(BaekjoonRank::label, e -> e));

    public static BaekjoonRank valueOfLabel(String label) {
        return BY_LABEL.get(label);
    }
}
