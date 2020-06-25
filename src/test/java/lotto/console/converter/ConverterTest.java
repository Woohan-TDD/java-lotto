package lotto.console.converter;

import static org.assertj.core.api.Assertions.*;

import java.util.Arrays;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class ConverterTest {

	@DisplayName("제대로 숫자 변환을 하는지 확인하는 테스트")
	@Test
	void numberConverterBy() {
		assertThat(Converter.numberConverterBy("1000")).isEqualTo(1000);
	}

	@DisplayName("숫자가 아닌 다른 값이 들어왔을 경우 예외처리")
	@ParameterizedTest
	@ValueSource(strings = {"가", "a", "A", "-", " ", ""})
	void numberConverterByNotNumber(String value) {
		assertThatThrownBy(() -> Converter.numberConverterBy(value)).isInstanceOf(NumberFormatException.class)
			.hasMessage("숫자만 입력 가능합니다. value = " + value);
	}

	@DisplayName(",을 기준으로 숫자가 들어왔을 경우 숫자로 변환후 리스트를 생성하는 테스트")
	@Test
	void numberListConverterBy() {
		assertThat(Converter.numberListConverterBy("1,2,3,4,5,6")).containsAll(Arrays.asList(1, 2, 3, 4, 5, 6));
	}
}
