package lotto.domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class LottoCountTest {

    @Test
    void validate_Negative() {
        assertThatThrownBy(() -> LottoCount.from(-1))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void calculateAutoCount() {
        Money money = Money.from(10_000);
        LottoCount lottoCount = LottoCount.from(3);
        assertThat(lottoCount.calculateAutoCount(money)).isEqualTo(7);
    }

    @Test
    void isNotZero_True() {
        LottoCount lottoCount = LottoCount.from(3);
        assertThat(lottoCount.isNotZero()).isTrue();
    }

    @Test
    void isNotZero_False() {
        LottoCount lottoCount = LottoCount.from(0);
        assertThat(lottoCount.isNotZero()).isFalse();
    }
}
