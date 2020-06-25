package domain.store;

import static org.assertj.core.api.Assertions.*;

import java.util.Arrays;
import java.util.Collections;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import domain.lotto.ticket.Lotto;
import domain.lotto.ticket.TestGenerateStrategy;

class LottoStoreTest {
	@Test
	@DisplayName("생성 테스트")
	void constructor() {
		assertThat(new LottoStore(new TestGenerateStrategy())).isNotNull();
	}

	@Test
	@DisplayName("전체 게임을 만드는 테스트")
	void createGame() {
		LottoStore lottoStore = new LottoStore(new TestGenerateStrategy());
		assertThat(lottoStore.createGame(new LottoPurchase(new Money(10000L), 1),
			Collections.singletonList(Lotto.valueOf(Arrays.asList(1, 2, 3, 4, 5, 7))))).isNotNull();
	}
}