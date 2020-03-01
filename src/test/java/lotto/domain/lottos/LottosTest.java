package lotto.domain.lottos;

import lotto.domain.lotto.Lotto;
import lotto.domain.lotto.LottoFactory;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullSource;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

/**
 * Lottos 테스트
 *
 * @author 토니, 히히
 * @version 1.0
 * <p>
 * 날짜 : 2020/02/19
 */
public class LottosTest {
	@Test
	void Lottos_생성자_올바른_동작_확인() {
		LottoFactory lottoFactory = new LottoFactory();

		List<Lotto> inputLottos = Arrays.asList(
				lottoFactory.createAutoLotto(),
				lottoFactory.createAutoLotto(),
				lottoFactory.createAutoLotto()
		);
		assertThat(new Lottos(inputLottos)).isInstanceOf(Lottos.class);
	}

	@ParameterizedTest
	@NullSource
	void Lottos_null_입력시_예외처리(List<Lotto> nullInput) {
		assertThatThrownBy(() -> {
			new Lottos(nullInput);
		}).isInstanceOf(NullPointerException.class)
				.hasMessage("입력이 null일 수 없습니다.");
	}
}