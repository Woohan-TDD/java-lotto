package domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullSource;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

class LottosTest {

    @ParameterizedTest
    @DisplayName("null 체크")
    @NullSource
    void checkNull(Object input) {
        assertThatThrownBy(() -> new Lottos((List<Lotto>) input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("로또는 1개 이상 구매해야 합니다.");
    }

}