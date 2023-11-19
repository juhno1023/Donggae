package Otwos.Donggae.Global.Rank;

public enum BaekjoonRank {
    Unrated(0),
    Bronze_I(6),
    Bronze_II(7),
    Bronze_III(8),
    Bronze_IV(9),
    Bronze_V(10),
    Silver_I(11),
    Silver_II(12),
    Silver_III(13),
    Silver_IV(14),
    Silver_V(15),
    Gold_I(16),
    Gold_II(17),
    Gold_III(18),
    Gold_IV(19),
    Gold_V(20),
    Platinum_I(21),
    Platinum_II(22),
    Platinum_III(23),
    Platinum_IV(24),
    Platinum_V(25),
    Diamond_I(26),
    Diamond_II(27),
    Diamond_III(28),
    Diamond_IV(29),
    Diamond_V(30),
    Ruby_I(31),
    Ruby_II(32),
    Ruby_III(33),
    Ruby_IV(34),
    Ruby_V(35);

    private final int score;

    BaekjoonRank(int score) {
        this.score = score;
    }

    public int getScore() {
        return score;
    }
}
