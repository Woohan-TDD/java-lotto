package domain.amount.element;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import domain.amount.LottoMoney;

class ManualLottoAmountTest {
	@DisplayName("수동 로또 생성시, 수량이 금액보다 많으면 에러가 발생한다.")
	@Test
	void name() {
		LottoMoney lottoMoney = new LottoMoney(1000);
		assertThatThrownBy(() -> ManualLottoAmount.of(10, lottoMoney))
			.isInstanceOf(IllegalArgumentException.class)
			.hasMessageContaining("수동로또 구매 갯수가 금액을 초과했습니다.");
	}
}