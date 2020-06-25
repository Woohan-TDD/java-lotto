package lotto.domain;

import java.util.Arrays;

public enum RankType {
    FIFTH(5_000, 3, false),
    FOURTH(50_000, 4, false),
    THIRD(1_500_000, 5, false),
    SECOND(30_000_000, 5, true),
    FIRST(2_000_000_000, 6, false),
    NONE(0, 0, false);

    private final int prize;
    private final int match;
    private final boolean bonus;

    RankType(int prize, int match, boolean bonus) {
        this.prize = prize;
        this.match = match;
        this.bonus = bonus;
    }

    public static RankType findType(LottoTicket lottoTicket, WinningLotto winningLotto) {
        return Arrays.stream(RankType.values())
                .filter(type -> type.isCorrectRank(lottoTicket, winningLotto))
                .findFirst()
                .orElse(NONE);
    }

    private boolean isCorrectRank(LottoTicket lottoTicket, WinningLotto winningLotto) {
        int match = winningLotto.getMatchCount(lottoTicket);
        boolean bonus = false;
        if (match == RankType.SECOND.match) {
            bonus = winningLotto.hasBonusByLottoTicket(lottoTicket);
        }
        return this.match == match && this.bonus == bonus;
    }

    public int getPrize() {
        return prize;
    }

    public int getMatch() {
        return match;
    }
}