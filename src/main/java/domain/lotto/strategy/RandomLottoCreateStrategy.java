package domain.lotto.strategy;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import domain.lotto.Lotto;
import domain.lotto.number.LottoNumber;

public class RandomLottoCreateStrategy implements LottoCreateStrategy {
	@Override
	public Lotto execute(List<LottoNumber> lottoNumbers) {
		ArrayList<LottoNumber> copiedLottoNumbers = new ArrayList<>(lottoNumbers);
		Collections.shuffle(copiedLottoNumbers);
		return new Lotto(copiedLottoNumbers.subList(0, Lotto.SIZE));
	}
}
