package lotto.domain.lottery;

import java.util.Arrays;
import java.util.function.BiPredicate;

public enum Rank {
    FIRST(6, (winningLotto, lottoTicket) ->
            winningLotto.match(lottoTicket) == 6 && winningLotto.excludeBonus(lottoTicket)
    ),
    SECOND(5, (winningLotto, lottoTicket) ->
            winningLotto.match(lottoTicket) == 5 && winningLotto.containsBonus(lottoTicket)
    ),
    THIRD(5, (winningLotto, lottoTicket) ->
            winningLotto.match(lottoTicket) == 5 && winningLotto.excludeBonus(lottoTicket)
    ),
    FOURTH(4, (winningLotto, lottoTicket) ->
            winningLotto.match(lottoTicket) == 4
    ),
    FIFTH(3, (winningLotto, lottoTicket) ->
            winningLotto.match(lottoTicket) == 3
    ),
    NOTHING(0, (winningLotto, lottoTicket) ->
            winningLotto.match(lottoTicket) < 3
    );

    private final int matchCount;
    private final BiPredicate<WinningLotto, LottoTicket> matchPredicate;

    Rank(final int matchCount, final BiPredicate<WinningLotto, LottoTicket> matchPredicate) {
        this.matchCount = matchCount;
        this.matchPredicate = matchPredicate;
    }

    static Rank match(final WinningLotto winningLotto, final LottoTicket lottoTicket) {
        return Arrays.stream(values())
                .filter(rank -> rank.matchRankWith(winningLotto, lottoTicket))
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }

    private boolean matchRankWith(final WinningLotto winningLotto, final LottoTicket lottoTicket) {
        return matchPredicate.test(winningLotto, lottoTicket);
    }
}
