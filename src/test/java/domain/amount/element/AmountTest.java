package domain.amount.element;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import domain.amount.LottoMoney;

class AmountTest {
	@DisplayName("수량은 음수를 입력하면 에러가 발생한다.")
	@Test
	void name() {
		assertThatThrownBy(() -> ManualLottoAmount.of(-1, new LottoMoney(1000)))
			.isInstanceOf(IllegalArgumentException.class)
			.hasMessageContaining("수량은 음수를 입력할 수 없습니다.");
	}

	@DisplayName("수량이 다른수와 비교해서 초과하는지 확인한다.")
	@CsvSource(value = {"0,true", "1,false"})
	@ParameterizedTest
	void name2(int other, boolean expect) {
		Amount amount = ManualLottoAmount.of(1, new LottoMoney(1000));

		assertThat(amount.isExcess(other)).isEqualTo(expect);
	}

	@DisplayName("수량의 뺄셈을 확인한다.")
	@CsvSource(value = {"5,0", "1,4"})
	@ParameterizedTest
	void name(int other, int expect) {
		Amount amount = ManualLottoAmount.of(5, new LottoMoney(5000));
		assertThat(amount.minus(other)).isEqualTo(expect);
	}
}