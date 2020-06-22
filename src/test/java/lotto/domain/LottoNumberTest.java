package lotto.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class LottoNumberTest {
    @DisplayName("숫자를 입력하여 로또 번호 생성")
    @ValueSource(ints = {1, 20, 45})
    @ParameterizedTest
    void constructor(final int number) {
        assertThat(new LottoNumber(number)).isInstanceOf(LottoNumber.class);
    }

    @DisplayName("로또 숫자의 범위를 벗어났을 때 예외 발생")
    @ValueSource(ints = {-1, 46})
    @ParameterizedTest
    void constructor_NumberRangeOutOfBound_ExceptionThrown(final int number) {
        assertThatThrownBy(() -> new LottoNumber(number))
                .isInstanceOf(InvalidLottoNumberException.class)
                .hasMessageContaining("로또 수의 범위가 올바르지 않습니다");
    }
}
