package domain.lotto.result;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class LottoMatchResultTest {
	@Test
	@DisplayName("생성 테스트")
	void constructor() {
		assertThat(new LottoMatchResult(6, true));
	}

	@Test
	@DisplayName("생성 테스트 - 음수 예외")
	void constructorWithNegativeException() {
		assertThatThrownBy(() -> new LottoMatchResult(-1, false))
			.isInstanceOf(IllegalArgumentException.class)
			.hasMessageContaining("음수");
	}

	@Test
	@DisplayName("생성 테스트 - 초과 예외")
	void constructorWithExceedException() {
		assertThatThrownBy(() -> new LottoMatchResult(7, false))
			.isInstanceOf(IllegalArgumentException.class)
			.hasMessageContaining("초과");
	}

	@ParameterizedTest
	@CsvSource(value = {"true,true", "false,false"})
	@DisplayName("매칭 결과가 보너스를 포함하는지 확인")
	void hasBonus(boolean bonus, boolean expected) {
		assertThat(new LottoMatchResult(5, bonus).hasBonus()).isEqualTo(expected);
	}

	@ParameterizedTest
	@CsvSource(value = {"1,true", "3,false"})
	@DisplayName("매칭되는 숫자가 일치하는 지 확인")
	void isEqualToMatchCount(int count, boolean expected) {
		assertThat(new LottoMatchResult(1, false).isEqualToMatchCount(count)).isEqualTo(expected);
	}

	@ParameterizedTest
	@CsvSource(value = {"1,false", "2,true", "3,true"})
	@DisplayName("매칭되는 숫자와 비교하여 더 큰 지 확인")
	void isEqualToOrGreaterThanMatchCount(int count, boolean expected) {
		assertThat(new LottoMatchResult(2, false).isEqualToOrGraterThanMatchCount(count)).isEqualTo(expected);
	}
}