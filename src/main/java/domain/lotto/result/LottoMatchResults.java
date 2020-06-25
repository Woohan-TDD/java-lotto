package domain.lotto.result;

import static java.util.stream.Collectors.*;

import java.util.List;

import domain.lotto.rank.RankCalculator;

public class LottoMatchResults {
	private final List<LottoMatchResult> lottoMatchResults;

	public LottoMatchResults(List<LottoMatchResult> lottoMatchResults) {
		this.lottoMatchResults = lottoMatchResults;
	}

	public LottoResult calculateResults(RankCalculator calculator) {
		return lottoMatchResults.stream()
			.map(calculator::calculate)
			.collect(collectingAndThen(toList(), LottoResult::new));
	}
}
