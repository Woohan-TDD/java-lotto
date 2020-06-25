package domain.lotto.ticket;

import static org.assertj.core.api.Assertions.*;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;

public class LottoTest {
	@Test
	@DisplayName("생성 테스트")
	void valueOf() {
		assertThat(Lotto.valueOf(Arrays.asList(1, 2, 3, 4, 5, 6))).isNotNull();
	}

	@Test
	@DisplayName("생성 테스트 - 중복된 숫자가 있을 경우 예외 처리")
	void valueOfWithException() {
		assertThatThrownBy(() -> Lotto.valueOf(Arrays.asList(1, 1, 2, 3, 4, 5)))
			.isInstanceOf(IllegalArgumentException.class)
			.hasMessageContaining("로또 숫자는");
	}

	@Test
	@DisplayName("생성 테스트 - 로또 넘버 갯수가 다를 경우 예외 처리")
	void valueOfWithSizeException() {
		assertThatThrownBy(() -> Lotto.valueOf(Arrays.asList(1, 2, 3, 4, 5)))
			.isInstanceOf(IllegalArgumentException.class)
			.hasMessageContaining("로또 숫자는");
	}

	@ParameterizedTest
	@DisplayName("로또 넘버가 로또에 포함되는 지 확인하는 테스트")
	@CsvSource(value = {"1,true", "2,true", "3,true", "4,true", "5,true", "6,true", "7,false"})
	void contains(int number, boolean expected) {
		Lotto lotto = Lotto.valueOf(Arrays.asList(1, 2, 3, 4, 5, 6));
		assertThat(lotto.contains(LottoNumber.of(number))).isEqualTo(expected);
	}

	@ParameterizedTest
	@DisplayName("다른 로또와 비교했을 때 매칭되는 숫자가 몇 개인지 확")
	@MethodSource("provideMatchedNumber")
	void countMatchedNumber(List<Integer> numbers, int expected) {
		Lotto lotto = Lotto.valueOf(Arrays.asList(1, 2, 3, 4, 5, 6));
		Lotto compared1 = Lotto.valueOf(numbers);

		assertThat(lotto.countMatchedNumber(compared1)).isEqualTo(expected);
	}

	private static Stream<Arguments> provideMatchedNumber() {
		return Stream.of(
			Arguments.of(Arrays.asList(1, 2, 3, 4, 5, 6), 6),
			Arguments.of(Arrays.asList(1, 2, 3, 4, 5, 7), 5),
			Arguments.of(Arrays.asList(1, 2, 3, 4, 7, 8), 4),
			Arguments.of(Arrays.asList(1, 2, 3, 7, 8, 9), 3),
			Arguments.of(Arrays.asList(1, 2, 7, 8, 9, 10), 2),
			Arguments.of(Arrays.asList(1, 7, 8, 9, 10, 11), 1),
			Arguments.of(Arrays.asList(7, 8, 9, 10, 11, 12), 0)
		);
	}
}
