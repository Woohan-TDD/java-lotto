package domain;

import java.util.Arrays;

public enum Rank {
    FIRST(2_000_000_000, 6, false, ""),
    SECOND(30_000_000, 5, true, "보너스 번호 일치"),
    THIRD(1_500_000, 5, false, ""),
    FOURTH(50_000, 4, false, ""),
    FIFTH(5_000, 3, false, ""),
    NONE(0, 0, false, "");

    private final int reward;
    private final int match;
    private final boolean bonus;
    private final String message;

    Rank(int reward, int match, boolean bonus, String message) {
        this.reward = reward;
        this.match = match;
        this.bonus = bonus;
        this.message = message;
    }

    public static Rank findRank(Lotto lotto, WinningLotto winningLotto) {
        return Arrays.stream(Rank.values())
                .filter(rank -> rank.isRank(lotto, winningLotto))
                .findFirst()
                .orElse(NONE);
    }

    private boolean isRank(Lotto lotto, WinningLotto winningLotto) {
        int match = winningLotto.calculateMatch(lotto);
        boolean isBonus = winningLotto.isBonus(lotto);
        return this.match == match && this.bonus == isBonus;
    }

    public int getReward() {
        return reward;
    }

    public int getMatch() {
        return match;
    }

    public String getMessage() {
        return message;
    }
}
