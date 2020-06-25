package lotto.domain;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class LottoNumberTest {

    @ParameterizedTest
    @ValueSource(ints = {0, 46})
    void validate_Number_OverRange(int input) {
        assertThatThrownBy(() -> LottoNumber.from(input))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @ParameterizedTest
    @ValueSource(ints = {1, 45})
    void validate_Number_Range(int input) {
        LottoNumber lottoNumber = LottoNumber.from(input);
        assertThat(lottoNumber).isNotNull();
    }
}