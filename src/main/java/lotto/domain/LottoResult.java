package lotto.domain;

import java.util.HashMap;
import java.util.Map;

public class LottoResult {

    private final Map<RankType, Integer> countRankType;
    private final Money money;

    public LottoResult(Map<RankType, Integer> countRankType, Money money) {
        this.countRankType = new HashMap<>(countRankType);
        this.money = money;
    }

    public static LottoResult of(Map<RankType, Integer> countRankType, Money money) {
        return new LottoResult(countRankType, money);
    }

    public int getRankCount(RankType type) {
        return countRankType.get(type);
    }

    public long getYield() {
        long totalPrize = calculateTotalPrize();
        return money.calculateYield(totalPrize);
    }

    private long calculateTotalPrize() {
        long sumPrize = 0L;
        for (RankType type : RankType.values()) {
            sumPrize += type.getPrize() * countRankType.get(type);
        }
        return sumPrize;
    }
}