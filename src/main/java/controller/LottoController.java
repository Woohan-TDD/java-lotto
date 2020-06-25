package controller;

import static java.util.stream.Collectors.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import domain.amount.LottoAmount;
import domain.amount.LottoMoney;
import domain.lotto.Lotto;
import domain.lotto.LottoFactory;
import domain.lotto.LottoManager;
import domain.lotto.LottoTickets;
import domain.lotto.WinningLotto;
import domain.lotto.number.LottoNumber;
import domain.lotto.strategy.LottoCreateStrategy;
import domain.statistics.Rank;
import view.InputView;
import view.OutputView;

public class LottoController {
	private final InputView inputView;
	private final OutputView outputView;
	private final LottoCreateStrategy lottoCreateStrategy;

	public LottoController(InputView inputView, OutputView outputView,
		LottoCreateStrategy lottoCreateStrategy) {
		this.inputView = inputView;
		this.outputView = outputView;
		this.lottoCreateStrategy = lottoCreateStrategy;
	}

	public void run() {
		LottoMoney lottoMoney = new LottoMoney(inputView.lottoMoney());
		LottoAmount lottoAmount = LottoAmount.valueOf(lottoMoney, inputView.manualLottoAmount());

		outputView.showManualLotto();
		LottoTickets lottoTickets = readManualLottoNumbers(lottoAmount);
		LottoTickets allLottoTickets = lottoTickets.concat(buyAutoLottos(lottoAmount));
		outputView.showPurchasedLotto(lottoAmount, allLottoTickets);

		WinningLotto winningLotto = readWinningLotto();

		LottoManager lottoManager = new LottoManager(allLottoTickets, winningLotto);
		Map<Rank, Integer> countByRank = lottoManager.calculateRanks();

		outputView.showWinningStatistics(countByRank);
		outputView.showProfit(lottoManager.calculateProfit(countByRank, lottoMoney));
	}

	private LottoTickets readManualLottoNumbers(LottoAmount lottoAmount) {
		List<Lotto> lottos = new ArrayList<>();

		while (lottoAmount.canBoughtManualLotto()) {
			lottos.add(
				inputView.manualLotto()
					.stream()
					.map(LottoNumber::valueOf)
					.collect(collectingAndThen(toList(), Lotto::new))
			);
			lottoAmount.nextManualLotto();
		}

		return new LottoTickets(lottos);
	}

	private List<Lotto> buyAutoLottos(LottoAmount lottoAmount) {
		return LottoFactory.createLotto(lottoAmount, lottoCreateStrategy);
	}

	private WinningLotto readWinningLotto() {
		Lotto lottoNumbers = inputView.winningLotto().stream()
			.map(LottoNumber::valueOf)
			.collect(collectingAndThen(toList(), Lotto::new));

		LottoNumber bonusNumber = LottoNumber.valueOf(inputView.bonusNumber());

		return new WinningLotto(lottoNumbers, bonusNumber);
	}
}
