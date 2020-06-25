package domain.lotto.result;

import static org.assertj.core.api.Assertions.*;

import java.util.Arrays;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import domain.lotto.rank.DefaultRankCalculator;

class LottoMatchResultsTest {
	@Test
	@DisplayName("생성 테스트")
	void constructor() {
		assertThat(new LottoMatchResults(Arrays.asList(
			new LottoMatchResult(6, false),
			new LottoMatchResult(5, true),
			new LottoMatchResult(4, false)
		))).isNotNull();
	}

	@Test
	@DisplayName("순위를 반환하는 기능 확인")
	void calculateResults() {
		LottoMatchResults lottoMatchResults = new LottoMatchResults(Arrays.asList(
			new LottoMatchResult(6, false),
			new LottoMatchResult(5, true),
			new LottoMatchResult(4, false)
		));

		assertThat(lottoMatchResults.calculateResults(new DefaultRankCalculator()));
	}
}