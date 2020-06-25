package domain.lotto;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import domain.amount.LottoMoney;
import domain.amount.element.ManualLottoAmount;

class LottoMoneyTest {
	@DisplayName("로또 금액은 1000원 이상으로 입력해야한다.")
	@Test
	void name() {
		assertThatThrownBy(() -> new LottoMoney(999))
			.isInstanceOf(IllegalArgumentException.class)
			.hasMessageContaining("로또의 금액은 1000원 이상이 필요합니다.");
	}

	@DisplayName("로또 금액과 로또 갯수를 비교하여 금액이 초과하는지 확인한다.")
	@CsvSource(value = {"2,true", "1,false"})
	@ParameterizedTest
	void isExcessAmount(int amount, boolean expect) {
		LottoMoney lottoMoney = new LottoMoney(1000);

		assertThat(lottoMoney.isExcessLottoAmount(amount)).isEqualTo(expect);
	}

	@DisplayName("수동로또를 구매하고 남은 로또수량을 계산한다.")
	@Test
	void calculateAutoLottoAmount() {
		LottoMoney lottoMoney = new LottoMoney(10000);
		ManualLottoAmount manualLottoAmount = ManualLottoAmount.of(1, lottoMoney);

		int amount = lottoMoney.calculateRemainderAmount(manualLottoAmount);

		assertThat(amount).isEqualTo(9);
	}

	@DisplayName("수익금을 입력받아 수익률을 낸다.")
	@Test
	void calculateProfit() {
		LottoMoney lottoMoney = new LottoMoney(10_000);
		double profit = lottoMoney.calculateProfit(1_000_000);

		assertThat(profit).isEqualTo(9900);
	}
}