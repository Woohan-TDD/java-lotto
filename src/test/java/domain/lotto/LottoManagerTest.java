package domain.lotto;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.Map;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import domain.amount.LottoMoney;
import domain.lotto.number.LottoNumber;
import domain.statistics.Rank;
import helper.LottoProvider;

class LottoManagerTest {
	@DisplayName("로또티켓들과 당첨번호를 이용하여, 각 등수별 합계를 구한다.")
	@Test
	void calculateRanks() {
		Lotto 일등 = LottoProvider.create(1, 2, 3, 4, 5, 6);
		Lotto 이등 = LottoProvider.create(1, 2, 3, 4, 5, 7);
		Lotto 삼등 = LottoProvider.create(1, 2, 3, 4, 5, 10);

		Lotto winningNumbers = LottoProvider.create(1, 2, 3, 4, 5, 6);
		WinningLotto winningLotto = new WinningLotto(winningNumbers, LottoNumber.valueOf(7));

		LottoManager lottoManager = new LottoManager(new LottoTickets(Arrays.asList(일등, 이등, 이등, 삼등, 삼등)), winningLotto);

		Map<Rank, Integer> actual = lottoManager.calculateRanks();

		assertAll(
			() -> assertThat(actual.get(Rank.SIX)).isEqualTo(1),
			() -> assertThat(actual.get(Rank.FIVE_BONUS)).isEqualTo(2),
			() -> assertThat(actual.get(Rank.FIVE)).isEqualTo(2)
		);
	}

	@DisplayName("등수별 합계와 배팅금을 통해 수익률을 계산한다.")
	@Test
	void calculateProfit() {
		Lotto 삼등 = LottoProvider.create(1, 2, 3, 4, 5, 10);

		Lotto winningNumbers = LottoProvider.create(1, 2, 3, 4, 5, 6);
		WinningLotto winningLotto = new WinningLotto(winningNumbers, LottoNumber.valueOf(7));

		LottoManager lottoManager = new LottoManager(new LottoTickets(Arrays.asList(삼등)), winningLotto);
		Map<Rank, Integer> countByRank = lottoManager.calculateRanks();

		LottoMoney lottoMoney = new LottoMoney(100_000L);

		double actual = lottoManager.calculateProfit(countByRank, lottoMoney);

		assertThat(actual).isEqualTo(1400);
	}
}