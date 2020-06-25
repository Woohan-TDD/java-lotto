package domain.store;

import java.util.ArrayList;
import java.util.List;

import domain.lotto.game.LottoGame;
import domain.lotto.ticket.Lotto;
import domain.lotto.ticket.LottoGenerateStrategy;

public class LottoStore {
	private static final long LOTTO_PRICE = 1_000L;
	private final LottoGenerateStrategy lottoGenerateStrategy;

	public LottoStore(LottoGenerateStrategy lottoGenerateStrategy) {
		this.lottoGenerateStrategy = lottoGenerateStrategy;
	}

	public LottoGame createGame(LottoPurchase lottoPurchase, List<Lotto> lottos) {
		if (lottoPurchase.isNotEqualToManualAmount(lottos.size())) {
			throw new IllegalArgumentException("수동 입력이 너무 많습니다. size : " + lottos.size() + " expected : " + lottoPurchase.getManualAmount());
		}
		List<Lotto> mergelottos = new ArrayList<>(lottos);
		for (int i = 0, autoAmount = getAutoAmount(lottoPurchase); i < autoAmount; i++) {
			mergelottos.add(lottoGenerateStrategy.createLotto());
		}
		return new LottoGame(mergelottos);
	}

	public int getAutoAmount(LottoPurchase lottoPurchase) {
		return lottoPurchase.calculateAutoAmount(LOTTO_PRICE);
	}
}
