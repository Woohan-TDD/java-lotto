package lotto.domain.lottery;

import java.util.Map;
import java.util.Objects;

public class MatchResult {
    private final Map<Rank, Long> rankCount;

    public MatchResult(final Map<Rank, Long> rankCount) {
        Objects.requireNonNull(rankCount, "결과가 존재하지 않습니다.");
        this.rankCount = rankCount;
    }

    public Long getCount(final Rank rank) {
        return rankCount.getOrDefault(rank, 0L);
    }
}
