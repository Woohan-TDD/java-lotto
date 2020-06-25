package domain.lotto;

import static org.assertj.core.api.Assertions.*;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import domain.amount.LottoAmount;
import domain.amount.LottoMoney;
import domain.lotto.number.LottoNumber;
import helper.LottoProvider;

class LottoFactoryTest {
	@DisplayName("자동 로또를 구매하여 로또를 확인한다.")
	@Test
	void testCreateLotto() {
		LottoAmount lottoAmount = LottoAmount.valueOf(new LottoMoney(1000), 0);
		List<Lotto> lotto = LottoFactory.createLotto(lottoAmount, lottoNumbers ->
			LottoProvider.create(1, 2, 3, 4, 5, 6)
		);

		List<LottoNumber> expect = IntStream.rangeClosed(1, 6)
			.mapToObj(LottoNumber::valueOf)
			.collect(Collectors.toList());

		List<LottoNumber> actual = lotto.get(0).getLottoNumbers();
		assertThat(actual).containsAll(expect);
	}
}