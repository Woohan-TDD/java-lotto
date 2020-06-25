package domain.lotto.ticket;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class RandomLottoGenerator implements LottoGenerateStrategy {
	@Override
	public Lotto createLotto() {
		List<Integer> lottoNumbers = new ArrayList<>(LottoNumber.CACHE.keySet());
		Collections.shuffle(lottoNumbers);
		return Lotto.valueOf(new ArrayList<>(lottoNumbers.subList(0, Lotto.SIZE)));
	}
}
