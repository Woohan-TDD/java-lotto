package domain.lotto;

import static org.assertj.core.api.Assertions.*;

import java.util.Arrays;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import domain.lotto.number.LottoNumber;
import helper.LottoProvider;

class WinningLottoTest {
	@DisplayName("당첨번호와 보너스 번호가 중복되면 에러가 발생한다.")
	@Test
	void name() {
		Lotto lotto = new Lotto(Arrays.asList(LottoNumber.valueOf(1), LottoNumber.valueOf(2), LottoNumber.valueOf(3)
			, LottoNumber.valueOf(4), LottoNumber.valueOf(5), LottoNumber.valueOf(6)));

		assertThatThrownBy(() -> new WinningLotto(lotto, LottoNumber.valueOf(1)))
			.isInstanceOf(IllegalArgumentException.class)
			.hasMessageContaining("당첨번호와 보너스번호는 중복될 수 없습니다.");
	}

	@DisplayName("로또 번호와 비교하여 몇개 맞았는지 확인한다.")
	@Test
	void countMatch() {
		Lotto lotto = LottoProvider.create(1, 2, 3, 4, 5, 6);
		LottoNumber bonus = LottoNumber.valueOf(7);
		WinningLotto winningLotto = new WinningLotto(lotto, bonus);

		int matchCount = winningLotto.countMatch(LottoProvider.create(1, 2, 3, 4, 5, 6));

		assertThat(matchCount).isEqualTo(6);
	}

	@DisplayName("보너스 번호가 있는지 확인한다.")
	@CsvSource(value = {"7,true", "6,false", "10,false"})
	@ParameterizedTest
	void checkBonus(int number, boolean expect) {
		Lotto lotto = LottoProvider.create(1, 2, 3, 4, 5, 6);
		LottoNumber bonus = LottoNumber.valueOf(7);
		WinningLotto winningLotto = new WinningLotto(lotto, bonus);

		boolean actual = winningLotto.checkBonus(LottoProvider.create(1, 2, 3, 4, 5, number));

		assertThat(actual).isEqualTo(expect);
	}
}
