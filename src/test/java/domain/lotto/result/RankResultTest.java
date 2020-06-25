package domain.lotto.result;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import domain.lotto.rank.LottoRank;

class RankResultTest {
	@Test
	@DisplayName("생성 테스트")
	void constructor() {
		assertThat(new RankResult(LottoRank.FIRST, 10)).isNotNull();
	}

	@Test
	@DisplayName("생성 테스트 예외")
	void constructorWithException() {
		assertThatThrownBy(() -> new RankResult(LottoRank.FIRST, -10))
			.isInstanceOf(IllegalArgumentException.class)
			.hasMessageContaining("음수");
	}
}