package lotto.domain.lottery;

import java.util.Map;
import java.util.Objects;

public class MatchResult {
    private static final int MAX_PERCENTAGE = 100;
    private final Map<Rank, Long> rankCount;

    public MatchResult(final Map<Rank, Long> rankCount) {
        Objects.requireNonNull(rankCount, "결과가 존재하지 않습니다.");
        this.rankCount = rankCount;
    }

    public long calculateProfitRate(final int totalSpendMoney) {
        return calculateTotalPrize() * MAX_PERCENTAGE / totalSpendMoney;
    }

    private long calculateTotalPrize() {
        return rankCount.entrySet()
                .stream()
                .mapToLong(entry -> entry.getKey().calculateTotalPrize(entry.getValue()))
                .sum();
    }

    public Long getCount(final Rank rank) {
        return rankCount.getOrDefault(rank, 0L);
    }
}
