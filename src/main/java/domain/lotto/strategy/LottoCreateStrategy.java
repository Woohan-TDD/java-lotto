package domain.lotto.strategy;

import java.util.List;

import domain.lotto.Lotto;
import domain.lotto.number.LottoNumber;

@FunctionalInterface
public interface LottoCreateStrategy {
	Lotto execute(List<LottoNumber> lottoNumbers);
}
