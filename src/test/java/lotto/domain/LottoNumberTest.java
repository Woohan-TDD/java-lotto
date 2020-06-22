package lotto.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class LottoNumberTest {
    @DisplayName("숫자를 입력하여 로또 번호 생성")
    @ValueSource(ints = {1, 20, 45})
    @ParameterizedTest
    void constructor(final int number) {
        assertThat(LottoNumber.valueOf(number)).isInstanceOf(LottoNumber.class);
    }

    @DisplayName("로또 숫자의 범위를 벗어났을 때 예외 발생")
    @ValueSource(ints = {-1, 46})
    @ParameterizedTest
    void constructor_NumberRangeOutOfBound_ExceptionThrown(final int number) {
        assertThatThrownBy(() -> LottoNumber.valueOf(number))
                .isInstanceOf(InvalidLottoNumberException.class)
                .hasMessageContaining("로또 수의 범위가 올바르지 않습니다");
    }

    @DisplayName("같은 값을 가지는 로또 객체는 논리적 동치성을 가짐")
    @Test
    void equals_equalNumber() {
        LottoNumber expect = LottoNumber.valueOf(20);
        LottoNumber actual = LottoNumber.valueOf(20);
        assertThat(actual.equals(expect)).isTrue();
    }

    @DisplayName("다른 값을 가지는 로또 객체는 논리적으로 다름")
    @Test
    void equals_DifferentNumber() {
        LottoNumber expect = LottoNumber.valueOf(20);
        LottoNumber actual = LottoNumber.valueOf(21);
        assertThat(actual.equals(expect)).isFalse();
    }

    @DisplayName("같은 값을 가지는 로또 객체가 같은 해시값을 가짐")
    @Test
    void hashCode_equalNumber() {
        LottoNumber expect = LottoNumber.valueOf(20);
        LottoNumber actual = LottoNumber.valueOf(20);
        assertThat(actual.hashCode()).isEqualTo(expect.hashCode());
    }
}
