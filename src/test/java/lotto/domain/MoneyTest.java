package lotto.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class MoneyTest {
    @DisplayName("로또 구입 금액을 입력하여 인스턴스를 생성한다.")
    @ValueSource(ints = {1_000, 5_000, 10_000})
    @ParameterizedTest
    void constructor(final int amount) {
        assertThat(new Money(amount)).isInstanceOf(Money.class);
    }

    @DisplayName("입력한 로또 구입 금액이 양수가 아닌 경우 예외가 발생한다.")
    @Test
    void constructor_ZeroOrNegativeAmount_ExceptionThrown() {
        int amount = -1 * 1_000;
        assertThatThrownBy(() -> new Money(amount))
                .isInstanceOf(InvalidMoneyAmountException.class)
                .hasMessageContaining("입력한 금액이 양수가 아닙니다");
    }

    @DisplayName("구입 금액이 1,000원 단위로 입력되지 않은 경우 예외가 발생한다.")
    @Test
    void constructor_MoneyUnitDivideRemainderIsOverZero_ExceptionThrown() {
        assertThatThrownBy(() -> new Money(10_100))
                .isInstanceOf(InvalidMoneyAmountException.class)
                .hasMessageContaining("입력한 금액이 1,000 단위로 나누어 떨어지지 않습니다");
    }
}
