package domain.lotto;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import domain.amount.LottoAmount;
import domain.lotto.number.LottoNumber;
import domain.lotto.strategy.LottoCreateStrategy;

public class LottoFactory {
	private static final List<LottoNumber> lottoNumbers = LottoNumber.getCachedLottoNumbers();

	public static List<Lotto> createLotto(LottoAmount lottoAmount, LottoCreateStrategy lottoCreateStrategy) {
		Objects.requireNonNull(lottoAmount);
		Objects.requireNonNull(lottoCreateStrategy);

		List<Lotto> lottos = new ArrayList<>();

		for (int i = 0; i < lottoAmount.getAutoLottoAmount().getAmount(); i++) {
			Lotto lotto = lottoCreateStrategy.execute(lottoNumbers);
			lottos.add(lotto);
		}

		return lottos;
	}
}
