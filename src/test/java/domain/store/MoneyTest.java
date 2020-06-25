package domain.store;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class MoneyTest {
	@Test
	@DisplayName("생성 테스트")
	void constructor() {
		assertThat(new Money(10L)).isNotNull();
	}

	@Test
	@DisplayName("생성 테스트 - 0이하일 경우 예외 처리")
	void constructorWithException() {
		assertThatThrownBy(() -> new Money(0L))
			.isInstanceOf(IllegalArgumentException.class)
			.hasMessageContaining("0보다");
	}

	@Test
	@DisplayName("주어진 값과의 비율을 구한다")
	void calculateRate() {
		assertThat(new Money(10L).calculateRate(100)).isEqualTo(10);
	}

	@Test
	@DisplayName("나누어 떨어지는 수인지 확인한다.")
	void isDividable() {
		assertThat(new Money(1000L).isDividable(100L)).isTrue();
	}

	@Test
	@DisplayName("가지고 있는 수를 주어진 값으로 나눈다.")
	void divideBy() {
		assertThat(new Money(1000L).divideBy(100L)).isEqualTo(10);
	}

	@Test
	@DisplayName("주어진 값이 가진 값보다 적거나 같은지 확인한다.")
	void isEqualToOrLessThan() {
		assertThat(new Money(1000L).isEqualToOrLessThan(1000L)).isTrue();
	}
}