package domain;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

public class WinningResult {

    private Map<Rank, Integer> winningResult = new LinkedHashMap<>();

    WinningResult() {
        for (Rank rank : Rank.values()) {
            winningResult.put(rank, 0);
        }
    }

    public WinningResult(Lottos lottos, WinningLotto winningLotto) {
        this();
        calculateWinningResult(lottos, winningLotto);
    }

    private void calculateWinningResult(Lottos lottos, WinningLotto winningLotto) {
        for (Lotto lotto : lottos.getLottos()) {
            Rank rank = Rank.findRank(lotto, winningLotto);
            winningResult.put(rank, winningResult.get(rank) + 1);
        }
    }

    public int calculateWinningMoney() {
        int money = 0;
        for (Map.Entry<Rank, Integer> result : winningResult.entrySet()) {
            money += result.getKey().getReward() * result.getValue();
        }
        return money;
    }

    public Map<Rank, Integer> getWinningResult() {
        return Collections.unmodifiableMap(winningResult);
    }
}
