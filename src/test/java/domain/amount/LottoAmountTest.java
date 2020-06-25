package domain.amount;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class LottoAmountTest {
	@DisplayName("수동 티켓 가격이 구매가격보다 크면 에러가 발생한다.")
	@Test
	void name() {
		assertThatThrownBy(() -> LottoAmount.valueOf(new LottoMoney(1000), 2))
			.isInstanceOf(IllegalArgumentException.class)
			.hasMessageContaining("수동로또 구매 갯수가 금액을 초과했습니다.");
	}

	@DisplayName("수동로또티켓을 입력받을 수 있는지 확인한다.")
	@Test
	void hasNext() {
		LottoAmount lottoAmount = LottoAmount.valueOf(new LottoMoney(1000), 1);

		assertThat(lottoAmount.canBoughtManualLotto()).isTrue();
	}

	@DisplayName("수동로또티켓의 카운트가 증가하는지 확인한다.")
	@Test
	void next() {
		LottoAmount lottoAmount = LottoAmount.valueOf(new LottoMoney(1000), 1);

		lottoAmount.nextManualLotto();

		assertThat(lottoAmount.getManualLottoCount()).isEqualTo(1);
	}

	@DisplayName("자동로또티켓을 구매할 수 있는지 확인한다.")
	@Test
	void canBoughtAutoLotto() {
		LottoAmount lottoAmount = LottoAmount.valueOf(new LottoMoney(1000), 0);

		boolean actual = lottoAmount.canBoughtAutoLotto();

		assertThat(actual).isTrue();
	}

	@DisplayName("자동로또티켓의 카운트가 증가하는지 확인한다.")
	@Test
	void nextAutoLotto() {
		LottoAmount lottoAmount = LottoAmount.valueOf(new LottoMoney(1000), 1);

		lottoAmount.nextAutoLotto();

		assertThat(lottoAmount.getAutoLottoCount()).isEqualTo(1);
	}
}