package utils;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EmptySource;
import org.junit.jupiter.params.provider.ValueSource;

import static java.util.Arrays.asList;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class StringUtilsTest {

    @Test
    @DisplayName("string->int 변경 예외처리 테스트")
    void parseInt() {
        String input = "스물셋";
        assertThatThrownBy(() -> StringUtils.parseInt("스물셋"))
                .isInstanceOf(NumberFormatException.class)
                .hasMessage("잘못된 문자 입력: " + input);
    }

    @ParameterizedTest
    @ValueSource(strings = {"1,2,3,4,5,6", "1, 2, 3, 4, 5, 6"})
    @DisplayName("정상적인 숫자들 입력이 들어왔을 때: , 기준으로 분리해서 List<Integer> 로 변환")
    void split(String input) {
        assertThat(StringUtils.splitToInteger(input))
                .isEqualTo(asList(1, 2, 3, 4, 5, 6));
    }

    @ParameterizedTest
    @EmptySource
    @ValueSource(strings = {"문자문자문자", "1.2.3.4.5", " "})
    @DisplayName("비정상적인 입력이 들어왔을 때, 예외처리 테스트")
    void splitException(String input) {
        assertThatThrownBy(() -> StringUtils.splitToInteger(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(",를 기준으로 숫자를 입력해야 합니다.");
    }

}