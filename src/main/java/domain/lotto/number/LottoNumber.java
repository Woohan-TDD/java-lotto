package domain.lotto.number;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class LottoNumber implements Comparable<LottoNumber> {
	public static final int MIN_LOTTO_NUMBER = 1;
	public static final int MAX_LOTTO_NUMBER = 45;

	private final int number;

	LottoNumber(int number) {
		validate(number);
		this.number = number;
	}

	public static LottoNumber valueOf(int number) {
		validate(number);
		return LottoNumberCache.LOTTO_NUMBER_BY_VALUE
			.get(number);
	}

	private static void validate(int number) {
		if (number < MIN_LOTTO_NUMBER || number > MAX_LOTTO_NUMBER) {
			throw new IllegalArgumentException("유효하지 않는 로또범위를 입력했습니다. number=" + number);
		}
	}

	public static List<LottoNumber> getCachedLottoNumbers() {
		return new ArrayList<>(LottoNumberCache.LOTTO_NUMBER_BY_VALUE.values());
	}

	@Override
	public int compareTo(LottoNumber other) {
		return Integer.compare(number, other.number);
	}

	@Override
	public String toString() {
		return "LottoNumber{" +
			"number=" + number +
			'}';
	}

	public int getNumber() {
		return number;
	}

	private static class LottoNumberCache {
		private static final Map<Integer, LottoNumber> LOTTO_NUMBER_BY_VALUE;

		static {
			LOTTO_NUMBER_BY_VALUE = IntStream.rangeClosed(MIN_LOTTO_NUMBER, MAX_LOTTO_NUMBER)
				.mapToObj(LottoNumber::new)
				.collect(Collectors.toMap(LottoNumber::getNumber, lottoNumber -> lottoNumber));
		}
	}
}
