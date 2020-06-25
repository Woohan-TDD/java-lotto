package helper;

import static java.util.stream.Collectors.*;

import java.util.Arrays;

import domain.lotto.Lotto;
import domain.lotto.number.LottoNumber;

public class LottoProvider {
	public static Lotto create(int... number) {
		return Arrays.stream(number)
			.mapToObj(LottoNumber::valueOf)
			.collect(collectingAndThen(toList(), Lotto::new));
	}
}
