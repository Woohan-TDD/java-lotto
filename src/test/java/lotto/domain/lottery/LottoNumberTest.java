package lotto.domain.lottery;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EmptySource;
import org.junit.jupiter.params.provider.NullSource;
import org.junit.jupiter.params.provider.ValueSource;

class LottoNumberTest {
    @DisplayName("숫자를 입력하여 로또 번호 생성")
    @ValueSource(ints = {1, 20, 45})
    @ParameterizedTest
    void valueOf_IntValue(final int number) {
        assertThat(LottoNumber.valueOf(number)).isInstanceOf(LottoNumber.class);
    }

    @DisplayName("로또 숫자의 범위를 벗어났을 때 예외 발생")
    @ValueSource(ints = {0, 46})
    @ParameterizedTest
    void valueOf_IntValue_NumberRangeOutOfBound_ExceptionThrown(final int number) {
        assertThatThrownBy(() -> LottoNumber.valueOf(number))
                .isInstanceOf(InvalidLottoNumberException.class)
                .hasMessageContaining("로또 수의 범위가 올바르지 않습니다");
    }

    @DisplayName("문자열을 입력하여 로또 번호 생성")
    @ValueSource(strings = {"1", "      20", "45     "})
    @ParameterizedTest
    void valueOf_StringValue(final String number) {
        assertThat(LottoNumber.valueOf(number)).isInstanceOf(LottoNumber.class);
    }

    @DisplayName("입력한 문자열이 숫자가 아닌 경우 예외 발생")
    @EmptySource
    @ValueSource(strings = {"ten", "십", "a"})
    @ParameterizedTest
    void valueOf_StringValueIsNotNumber_ExceptionThrown(final String number) {
        assertThatThrownBy(() -> LottoNumber.valueOf(number)).isInstanceOf(NumberFormatException.class);
    }

    @DisplayName("입력한 문자열이 null인 경우 예외 발생")
    @NullSource
    @ParameterizedTest
    void valueOf_StringValueIsNull_ExceptionThrown(final String number) {
        assertThatThrownBy(() -> LottoNumber.valueOf(number))
                .isInstanceOf(NullPointerException.class)
                .hasMessageContaining("입력한 값이 존재하지 않습니다");
    }

    @DisplayName("생성된 로또 수의 값을 확인")
    @Test
    void getNumber() {
        LottoNumber lottoNumber = LottoNumber.valueOf(20);
        assertThat(lottoNumber.getNumber()).isEqualTo(20);
    }

    @DisplayName("같은 값을 가지는 로또 객체는 논리적 동치성을 가짐")
    @Test
    void equals_EqualNumber() {
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
    void hashCode_EqualNumber() {
        LottoNumber expect = LottoNumber.valueOf(20);
        LottoNumber actual = LottoNumber.valueOf(20);
        assertThat(actual.hashCode()).isEqualTo(expect.hashCode());
    }
}
