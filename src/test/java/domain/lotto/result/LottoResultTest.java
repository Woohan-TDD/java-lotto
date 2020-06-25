package domain.lotto.result;

import static org.assertj.core.api.Assertions.*;

import java.util.Arrays;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import domain.lotto.rank.LottoRank;
import domain.store.WinningMoney;

public class LottoResultTest {
	@Test
	@DisplayName("생성 테스트")
	void constructor() {
		assertThat(new LottoResult(Arrays.asList(LottoRank.FIRST, LottoRank.SECOND))).isNotNull();
	}

	@Test
	@DisplayName("로또 게임의 총 상금을 계산하는지 확인")
	void calculateWinning() {
		assertThat(new LottoResult(Arrays.asList(LottoRank.FIFTH, LottoRank.FORTH)).calculateWinning()).isEqualTo(
			new WinningMoney(55_000L));
	}

	@Test
	@DisplayName("각 로또 등수별 당첨 수 확인")
	void countRank() {
		LottoResult lottoResult = new LottoResult(Arrays.asList(LottoRank.FIFTH, LottoRank.FORTH));
		assertThat(lottoResult.countRank(LottoRank.FIFTH)).extracting("count").isEqualTo(1);
	}
}
