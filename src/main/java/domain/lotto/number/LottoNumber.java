package domain.lotto.number;

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
		return LottoNumberCache.lottoNumberByNumber
			.get(number);
	}

	private static void validate(int number) {
		if (number < MIN_LOTTO_NUMBER || number > MAX_LOTTO_NUMBER) {
			throw new IllegalArgumentException("유효하지 않는 로또범위를 입력했습니다. number=" + number);
		}
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

	static class LottoNumberCache {
		private static final Map<Integer, LottoNumber> lottoNumberByNumber;

		static {
			lottoNumberByNumber = IntStream.rangeClosed(MIN_LOTTO_NUMBER, MAX_LOTTO_NUMBER)
				.mapToObj(LottoNumber::new)
				.collect(Collectors.toMap(LottoNumber::getNumber, lottoNumber -> lottoNumber));
		}
	}
}
