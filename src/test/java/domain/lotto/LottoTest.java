package domain.lotto;

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

import domain.lotto.number.LottoNumber;
import helper.LottoProvider;

class LottoTest {

	private static Stream<Arguments> lottoNumberProvider() {
		return Stream.of(
			Arguments.of(
				Arrays.asList(
					LottoNumber.valueOf(1), LottoNumber.valueOf(2), LottoNumber.valueOf(3)
					, LottoNumber.valueOf(4), LottoNumber.valueOf(5)))
			, Arguments.of(
				Arrays.asList(
					LottoNumber.valueOf(1), LottoNumber.valueOf(2), LottoNumber.valueOf(3)
					, LottoNumber.valueOf(4), LottoNumber.valueOf(5), LottoNumber.valueOf(6)
					, LottoNumber.valueOf(7))
			)
		);
	}

	@DisplayName("로또는 로또넘버가 6개가 아니면 에러가 발생한다.")
	@MethodSource("lottoNumberProvider")
	@ParameterizedTest
	void name(List<LottoNumber> lottoNumbers) {
		assertThatThrownBy(() -> new Lotto(lottoNumbers))
			.isInstanceOf(IllegalArgumentException.class)
			.hasMessage("로또는 로또넘버 6개로 만들 수 있습니다. lottoNumbers=" + lottoNumbers);
	}

	@DisplayName("로또번호가 서로 중복되지 않아야 한다.")
	@Test
	void name() {
		List<LottoNumber> lottoNumbers = Arrays.asList(LottoNumber.valueOf(1), LottoNumber.valueOf(2),
			LottoNumber.valueOf(3)
			, LottoNumber.valueOf(4), LottoNumber.valueOf(4), LottoNumber.valueOf(6));

		assertThatThrownBy(() -> new Lotto(lottoNumbers))
			.isInstanceOf(IllegalArgumentException.class)
			.hasMessageContaining("로또 번호는 중복될 수 없습니다.");
	}

	@DisplayName("Lotto에서 LottoNumber가 있는지 확인한다.")
	@CsvSource(value = {"1,true", "10,false"})
	@ParameterizedTest
	void isContains(int number, boolean expect) {
		List<LottoNumber> lottoNumbers = Arrays.asList(LottoNumber.valueOf(1), LottoNumber.valueOf(2),
			LottoNumber.valueOf(3)
			, LottoNumber.valueOf(4), LottoNumber.valueOf(5), LottoNumber.valueOf(6));

		Lotto lotto = new Lotto(lottoNumbers);
		LottoNumber lottoNumber = LottoNumber.valueOf(number);

		assertThat(lotto.contains(lottoNumber)).isEqualTo(expect);
	}

	@DisplayName("로또 번호와 비교하여 몇개 맞았는지 확인한다.")
	@Test
	void countMatch() {
		Lotto lotto = LottoProvider.create(1, 2, 3, 4, 5, 6);
		Lotto other = LottoProvider.create(1, 2, 3, 4, 5, 6);

		assertThat(lotto.countMatch(other)).isEqualTo(6);
	}
}