package view;

import domain.Lotto;
import domain.Lottos;
import domain.Rank;
import domain.purchase.PurchaseCounts;

import java.util.Map;

public class OutputView {
    public void showPurchaseCount(PurchaseCounts purchaseCounts) {
        System.out.printf("자동으로 %d장, 수동으로 %d장을 구입했습니다.\n", purchaseCounts.getAutoCount(), purchaseCounts.getManualCount());
    }

    public void showLottos(Lottos lottos) {
        for (Lotto lottoNumbers : lottos.getLottos()) {
            showLotto(lottoNumbers);
        }
    }

    private void showLotto(Lotto lotto) {
        System.out.println(lotto.toString());
    }

    public void showWinningResult(Map<Rank, Integer> winningResult) {
        System.out.println("당첨 통계");
        System.out.println("------------");
        for (Map.Entry<Rank, Integer> result : winningResult.entrySet()) {
            System.out.printf("%d개 일치,%s (%d원) - %d개\n",
                    result.getKey().getMatch(), result.getKey().getMessage(), result.getKey().getReward(), result.getValue());
        }
    }

    public void showWinningRate(double winningRate) {
        System.out.println("총 수익률은 " + winningRate + "% 입니다.");
    }
}
