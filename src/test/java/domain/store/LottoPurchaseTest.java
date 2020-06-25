package domain.store;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class LottoPurchaseTest {
	@Test
	@DisplayName("생성 테스트")
	void constructor() {
		assertThat(new LottoPurchase(new Money(1000L), 1)).isNotNull();
	}

	@Test
	@DisplayName("생성 테스트 - 음수 시 예외")
	void constructorWithException() {
		assertThatThrownBy(() -> new LottoPurchase(new Money(1000L), -1))
			.isInstanceOf(IllegalArgumentException.class)
			.hasMessageContaining("음수");
	}

	@Test
	@DisplayName("자동 구매 횟수 계산하는 기능")
	void calculateAutoAmount() {
		LottoPurchase lottoPurchase = new LottoPurchase(new Money(3000L), 1);
		assertThat(lottoPurchase.calculateAutoAmount(1000L)).isEqualTo(2);
	}

	@Test
	@DisplayName("자동 구매 횟수 계산하는 기능 - 수동 횟수가 너무 많을 경우 예외")
	void calculateAutoAmountExceedException() {
		LottoPurchase lottoPurchase = new LottoPurchase(new Money(3000L), 1);
		assertThatThrownBy(() -> lottoPurchase.calculateAutoAmount(4000L))
			.isInstanceOf(IllegalArgumentException.class)
			.hasMessageContaining("수동 구매 횟수");
	}

	@Test
	@DisplayName("자동 구매 횟수 계산하는 기능 - 로또 가격이 0이하일 경우")
	void calculateAutoAmountNegativeException() {
		LottoPurchase lottoPurchase = new LottoPurchase(new Money(3000L), 1);
		assertThatThrownBy(() -> lottoPurchase.calculateAutoAmount(-4000L))
			.isInstanceOf(IllegalArgumentException.class)
			.hasMessageContaining("0이하");
	}

	@Test
	@DisplayName("자동 구매 횟수 계산하는 기능 - 거스름돈이 남을 경우")
	void calculateAutoAmountNotDividedException() {
		LottoPurchase lottoPurchase = new LottoPurchase(new Money(3000L), 1);
		assertThatThrownBy(() -> lottoPurchase.calculateAutoAmount(7L))
			.isInstanceOf(IllegalArgumentException.class)
			.hasMessageContaining("거스름돈");
	}

	@Test
	@DisplayName("수동 입력 횟수와 같은 지 확인하는 기능")
	void isEqualToManualAmount() {
		LottoPurchase lottoPurchase = new LottoPurchase(new Money(3000L), 1);
		assertThat(lottoPurchase.isNotEqualToManualAmount(1)).isFalse();
	}
}