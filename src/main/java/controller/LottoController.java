package controller;

import domain.Lotto;
import domain.Lottos;
import domain.purchase.Count;
import domain.purchase.Money;
import domain.purchase.PurchaseCounts;
import view.InputView;
import view.OutputView;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class LottoController {

    private final InputView inputView;
    private final OutputView outputView;

    public LottoController(final InputView inputView, final OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void run() {
        PurchaseCounts purchaseCounts = buyLotto();
        Lottos lottos = generateLottos(purchaseCounts);
        showLottos(lottos, purchaseCounts);
    }

    private PurchaseCounts buyLotto() {
        Money money = new Money(inputView.inputMoney());
        Count manualCount = Count.createManualCount(money, inputView.inputManualCount());
        return new PurchaseCounts(Count.createAutoCount(money, manualCount), manualCount);
    }

    private Lottos generateLottos(PurchaseCounts purchaseCounts) {
        List<Lotto> lottos = generateManualLottos(purchaseCounts.getManualCount());
        lottos.addAll(generateAutoLottos(purchaseCounts.getAutoCount()));
        return new Lottos(lottos);
    }

    private List<Lotto> generateAutoLottos(int autoCount) {
        List<Lotto> lottos = new ArrayList<>();
        for (int i = 0; i < autoCount; i++) {
            lottos.add(Lotto.createAutoLotto());
        }
        return lottos;
    }

    private List<Lotto> generateManualLottos(int manualCount) {
        return inputView.inputManualLottoNumbers(manualCount)
                .stream()
                .map(Lotto::createManualLotto)
                .collect(Collectors.toList());
    }

    private void showLottos(Lottos lottos, PurchaseCounts purchaseCounts) {
        outputView.showPurchaseCount(purchaseCounts);
        outputView.showLottos(lottos);
    }

}
