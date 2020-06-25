package domain.lotto.rank;

import domain.lotto.result.LottoMatchResult;

public class DefaultRankCalculator implements RankCalculator {
	@Override
	public LottoRank calculate(LottoMatchResult lottoMatchResult) {
		return LottoRank.findRank(lottoMatchResult);
	}
}
