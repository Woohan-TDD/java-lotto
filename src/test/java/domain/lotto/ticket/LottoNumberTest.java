package domain.lotto.ticket;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class LottoNumberTest {
	@ParameterizedTest
	@ValueSource(ints = {1, 45})
	@DisplayName("로또 넘버 생성자")
	void of(int number) {
		assertThat(LottoNumber.of(number)).isNotNull();
	}

	@ParameterizedTest
	@ValueSource(strings = {"1", "45"})
	@DisplayName("로또 넘버 생성자 - 문자")
	void ofString(int number) {
		assertThat(LottoNumber.of(number)).isNotNull();
	}

	@ParameterizedTest
	@ValueSource(ints = {0, -1, 46, 1000})
	void ofWithException(int number) {
		assertThatThrownBy(() -> LottoNumber.of(number))
			.isInstanceOf(IllegalArgumentException.class)
			.hasMessageContaining("해당하는");
	}
}
