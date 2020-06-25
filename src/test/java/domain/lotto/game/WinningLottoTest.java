package domain.lotto.game;

import static org.assertj.core.api.Assertions.*;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import domain.lotto.result.LottoMatchResult;
import domain.lotto.ticket.Lotto;
import domain.lotto.ticket.LottoNumber;

public class WinningLottoTest {
	@Test
	@DisplayName("생성 테스트")
	void constructor() {
		Lotto lotto = Lotto.valueOf(Arrays.asList(1, 2, 3, 4, 5, 6));
		assertThat(new WinningLotto(lotto, LottoNumber.of(7))).isNotNull();
	}

	@ParameterizedTest
	@DisplayName("등수 생성을 확인하는 테스트")
	@MethodSource("provideMatchedResult")
	void calculateMatchResult(List<Integer> numbers, LottoMatchResult expected) {
		WinningLotto winningLotto = new WinningLotto(Lotto.valueOf(Arrays.asList(1, 2, 3, 4, 5, 6)),
			LottoNumber.of(45));
		assertThat(winningLotto.calculateMatchResult(Lotto.valueOf(numbers))).isEqualTo(expected);
	}

	private static Stream<Arguments> provideMatchedResult() {
		return Stream.of(
			Arguments.of(Arrays.asList(1, 2, 3, 4, 5, 6), new LottoMatchResult(6, false)),
			Arguments.of(Arrays.asList(1, 2, 3, 4, 5, 45), new LottoMatchResult(5, true)),
			Arguments.of(Arrays.asList(1, 2, 3, 4, 5, 7), new LottoMatchResult(5, false)),
			Arguments.of(Arrays.asList(1, 2, 3, 4, 7, 8), new LottoMatchResult(4, false)),
			Arguments.of(Arrays.asList(1, 2, 3, 7, 8, 9), new LottoMatchResult(3, false)),
			Arguments.of(Arrays.asList(1, 2, 7, 8, 9, 10), new LottoMatchResult(2, false)),
			Arguments.of(Arrays.asList(1, 7, 8, 9, 10, 11), new LottoMatchResult(1, false)),
			Arguments.of(Arrays.asList(7, 8, 9, 10, 11, 12), new LottoMatchResult(0, false))
		);
	}
}
