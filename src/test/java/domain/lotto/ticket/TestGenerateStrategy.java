package domain.lotto.ticket;

import java.util.Arrays;

public class TestGenerateStrategy implements LottoGenerateStrategy {
	@Override
	public Lotto createLotto() {
		return Lotto.valueOf(Arrays.asList(1, 2, 3, 4, 5, 6));
	}
}
