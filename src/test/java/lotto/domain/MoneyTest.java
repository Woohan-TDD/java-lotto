package lotto.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class MoneyTest {

    private Money money;

    @BeforeEach
    void setUp() {
        money = Money.from(10_000L);
    }

    @ParameterizedTest
    @ValueSource(longs = {-100, -1})
    void validate_Negative(long input) {
        assertThatThrownBy(() -> Money.from(input))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @ParameterizedTest
    @ValueSource(longs = {1, 12, 123, 1234})
    void validate_Unit(long input) {
        assertThatThrownBy(() -> Money.from(input))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void create() {
        Money money = Money.from(10000);
        assertThat(money).isNotNull();
    }

    @Test
    void calculateAutoCount() {
        assertThat(money.calculateAutoCount(3)).isEqualTo(7);
    }

    @Test
    void calculateAutoCount_Error() {
        assertThatThrownBy(() -> money.calculateAutoCount(11))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void calculateYield() {
        assertThat(money.calculateYield(120_000L)).isEqualTo(1_100L);
    }
}
