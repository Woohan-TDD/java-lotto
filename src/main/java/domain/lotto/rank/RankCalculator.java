package domain.lotto.rank;

import domain.lotto.result.LottoMatchResult;

public interface RankCalculator {
	LottoRank calculate(LottoMatchResult lottoMatchResult);
}
