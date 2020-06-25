package domain.lotto.game;

import static org.assertj.core.api.Assertions.*;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import domain.lotto.ticket.Lotto;
import domain.lotto.ticket.LottoNumber;

public class LottoGameTest {
	private List<Lotto> lottos;

	@BeforeEach
	void setUp() {
		lottos = Arrays.asList(Lotto.valueOf(Arrays.asList(1, 2, 3, 4, 5, 6)),
			Lotto.valueOf(Arrays.asList(1, 2, 3, 4, 5, 6)),
			Lotto.valueOf(Arrays.asList(1, 2, 3, 4, 5, 6)),
			Lotto.valueOf(Arrays.asList(1, 2, 3, 4, 5, 6)));
	}

	@Test
	@DisplayName("생성 테스트")
	void constructor() {
		assertThat(new LottoGame(lottos)).isNotNull();
	}

	@Test
	@DisplayName("로또 게임 결과를 생성")
	void calculateResults() {
		WinningLotto winningLotto = new WinningLotto(Lotto.valueOf(Arrays.asList(1, 2, 3, 4, 5, 6)),
			LottoNumber.of(45));
		LottoGame lottoGame = new LottoGame(lottos);
		assertThat(lottoGame.calculateResults(winningLotto)).isNotNull();
	}
}
