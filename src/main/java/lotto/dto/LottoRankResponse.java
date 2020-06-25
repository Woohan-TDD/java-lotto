package lotto.dto;

import lotto.domain.lottery.Rank;

public class LottoRankResponse {
    private Long numberOfMatch;
    private Long prize;
    private boolean isBonus;
    private Long count;

    public LottoRankResponse(final Long numberOfMatch, final Long prize, final boolean isBonus, final Long count) {
        this.numberOfMatch = numberOfMatch;
        this.prize = prize;
        this.isBonus = isBonus;
        this.count = count;
    }

    public static LottoRankResponse fromRank(final Rank rank, final Long count) {
        return new LottoRankResponse((long)rank.getMatchCount(), rank.getPrize(), Rank.SECOND.equals(rank), count);
    }

    public Long getNumberOfMatch() {
        return numberOfMatch;
    }

    public void setNumberOfMatch(final Long numberOfMatch) {
        this.numberOfMatch = numberOfMatch;
    }

    public Long getPrize() {
        return prize;
    }

    public void setPrize(final Long prize) {
        this.prize = prize;
    }

    public boolean isBonus() {
        return isBonus;
    }

    public void setBonus(final boolean bonus) {
        isBonus = bonus;
    }

    public Long getCount() {
        return count;
    }

    public void setCount(final Long count) {
        this.count = count;
    }
}
