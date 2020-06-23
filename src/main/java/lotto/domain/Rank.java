package lotto.domain;

public enum Rank {
    FIRST(2_000_000_000, 6, false),
    SECOND(30_000_000, 5, true),
    THIRD(1_500_000, 5, false),
    FOURTH(50_000, 4, false),
    FIFTH(5_000, 3, false);

    private final int prize;
    private final int match;
    private final boolean hasBonusBall;

    Rank(int prize, int match, boolean hasBonusBall) {
        this.prize = prize;
        this.match = match;
        this.hasBonusBall = hasBonusBall;
    }
}
