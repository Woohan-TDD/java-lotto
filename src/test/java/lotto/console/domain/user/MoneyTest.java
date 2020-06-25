package lotto.console.domain.user;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class MoneyTest {

	@DisplayName("돈이 제대로 생성되는지 확인하는 테스트")
	@Test
	void createCorrectMoney() {
		Money money = new Money(1000);
		assertThat(money.getMoney()).isEqualTo(1000);
	}

	@DisplayName("천원 단위로 입력하지 않았을 경우 예외처리")
	@Test
	void validateUnitThousandWon() {
		assertThatThrownBy(() -> new Money(1100)).isInstanceOf(IllegalArgumentException.class)
			.hasMessage("천원 단위로만 입력이 가능합니다. money = 1100");
	}

	@DisplayName("금액이 음수일 경우 예외처리")
	@Test
	void validateNegativeMoney() {
		assertThatThrownBy(() -> new Money(-1000)).isInstanceOf(IllegalArgumentException.class)
			.hasMessage("금액은 음수가 될 수 없습니다. money = -1000");
	}
}