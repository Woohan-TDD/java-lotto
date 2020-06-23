package lotto.domain.lottery;

import java.util.Arrays;
import java.util.function.BiPredicate;

public enum Rank {
    FIRST(6, 2_000_000_000, (matchCount, isBonus) -> matchCount == 6 && !isBonus),
    SECOND(5, 30_000_000, (matchCount, isBonus) -> matchCount == 5 && isBonus),
    THIRD(5, 1_500_000, (matchCount, isBonus) -> matchCount == 5 && !isBonus),
    FOURTH(4, 50_000, (matchCount, isBonus) -> matchCount == 4),
    FIFTH(3, 5_000, (matchCount, isBonus) -> matchCount == 3),
    NOTHING(0, 0, (matchCount, isBonus) -> matchCount < 3);

    private final int matchCount;
    private final long prize;
    private final BiPredicate<Integer, Boolean> matchPredicate;

    Rank(final int matchCount, final long prize, final BiPredicate<Integer, Boolean> matchPredicate) {
        this.matchCount = matchCount;
        this.prize = prize;
        this.matchPredicate = matchPredicate;
    }

    public static Rank match(final int matchCount, final boolean isBonus) {
        return Arrays.stream(values())
                .filter(rank -> rank.matchRankWith(matchCount, isBonus))
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }

    public long calculateTotalPrize(int count) {
        return prize * count;
    }

    private boolean matchRankWith(final Integer winningLotto, final Boolean lottoTicket) {
        return matchPredicate.test(winningLotto, lottoTicket);
    }
}
