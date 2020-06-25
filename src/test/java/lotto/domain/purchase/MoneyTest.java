package lotto.domain.purchase;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

class MoneyTest {
    @DisplayName("로또 구입 금액을 입력하여 인스턴스를 생성")
    @ValueSource(ints = {0, 1_000, 5_000, 10_000})
    @ParameterizedTest
    void constructor(final int amount) {
        assertThat(new Money(amount)).isInstanceOf(Money.class);
    }

    @DisplayName("입력한 로또 구입 금액이 0 또는 양수가 아닌 경우 예외가 발생")
    @Test
    void constructor_ZeroOrNegativeAmount_ExceptionThrown() {
        int amount = -1 * 1_000;
        assertThatThrownBy(() -> new Money(amount))
                .isInstanceOf(InvalidMoneyAmountException.class)
                .hasMessageContaining("입력한 금액이 양수가 아닙니다");
    }

    @DisplayName("구입 금액이 1,000원 단위로 입력되지 않은 경우 예외가 발생")
    @Test
    void constructor_MoneyUnitDivideRemainderIsOverZero_ExceptionThrown() {
        assertThatThrownBy(() -> new Money(10_100))
                .isInstanceOf(InvalidMoneyAmountException.class)
                .hasMessageContaining("입력한 금액이 1,000 단위로 나누어 떨어지지 않습니다");
    }

    @DisplayName("입력받은 만큼의 돈을 소비")
    @CsvSource(value = {"1000, 9000", "10000, 0"})
    @ParameterizedTest
    void spend(final int spendAmount, final int expectAmount) {
        Money expect = new Money(expectAmount);
        Money actual = new Money(10_000);
        Money spendMoney = new Money(spendAmount);

        actual.spend(spendMoney);

        assertThat(actual).isEqualTo(expect);
    }

    @DisplayName("돈을 0원 이상 가지고 있는지 확인")
    @CsvSource(value = {"1000, true", "0,false"})
    @ParameterizedTest
    void hasAvailableAmount(final int amount, final boolean expect) {
        Money money = new Money(amount);

        assertThat(money.hasAvailableAmount()).isEqualTo(expect);
    }

    @DisplayName("소비하고자 하는 양만큼의 금액이 없는 경우 예외 발생")
    @Test
    void spend_LackOfMoney_ExceptionThrown() {
        Money money = new Money(1_000);
        Money spendMoney = new Money(2_000);

        assertThatThrownBy(() -> money.spend(spendMoney))
                .isInstanceOf(LackOfMoneyException.class)
                .hasMessageContaining("금액이 부족합니다");
    }

    @DisplayName("생성된 money의 값을 확인")
    @Test
    void getAmount() {
        Money money = new Money(10_000);
        assertThat(money.getAmount()).isEqualTo(10_000);
    }
}
