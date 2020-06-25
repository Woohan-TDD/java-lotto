package domain.lotto.number;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class LottoNumberTest {
	@DisplayName("로또를 생성할 때 1~45를 제외한 수로 입력 시 에러를 발생한다.")
	@ValueSource(ints = {0, 46})
	@ParameterizedTest
	void name(int badNumber) {
		assertThatThrownBy(() -> new LottoNumber(badNumber))
			.isInstanceOf(IllegalArgumentException.class)
			.hasMessage("유효하지 않는 로또범위를 입력했습니다. number=" + badNumber);
	}

	@DisplayName("LottoNumber는 Single instance이다.")
	@Test
	void name2() {
		LottoNumber lottoNumber = LottoNumber.valueOf(1);
		LottoNumber other = LottoNumber.valueOf(1);

		assertThat(lottoNumber == other).isTrue();
	}
}