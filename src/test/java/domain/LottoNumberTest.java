package domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class LottoNumberTest {

    @ParameterizedTest
    @ValueSource(ints = {1, 45})
    @DisplayName("범위 내(1-45)의 로또 숫자 생성 테스트")
    void validateLottoNumber(int value) {
        LottoNumber lottoNumber = new LottoNumber(value);
        assertThat(lottoNumber).isNotNull();
    }

    @ParameterizedTest
    @ValueSource(ints = {0, 46})
    @DisplayName("범위 밖의 로또 숫자에 대한 유효성 테스트")
    void validateLottoNumberScope(int value) {
        assertThatThrownBy(() -> new LottoNumber(value))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("범위 내의 로또 숫자가 아닙니다.");
    }

    @Test
    @DisplayName("해당 숫자가 맞는지 확인")
    void isLottoNumber() {
        LottoNumber lottoNumber = new LottoNumber(3);
        assertThat(lottoNumber.isLottoNumber(3)).isTrue();
        assertThat(lottoNumber.isLottoNumber(4)).isFalse();
    }
}