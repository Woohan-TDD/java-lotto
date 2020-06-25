package domain.lotto.ticket;

import java.util.HashMap;
import java.util.Map;

public class LottoNumber implements Comparable<LottoNumber> {
	public static Map<Integer, LottoNumber> CACHE = new HashMap<>();
	private static final int MIN_LOTTO_NUMBER = 1;
	private static final int MAX_LOTTO_NUMBER = 45;

	static {
		for (int i = MIN_LOTTO_NUMBER; i <= MAX_LOTTO_NUMBER; i++) {
			CACHE.put(i, new LottoNumber(i));
		}
	}

	private final int lottoNumber;

	private LottoNumber(int number) {
		this.lottoNumber = number;
	}

	private static void validate(int number) {
		if (!CACHE.containsKey(number)) {
			throw new IllegalArgumentException("로또 번호에 해당하는 값을 입력해주세요. input : " + number);
		}
	}

	public static LottoNumber of(int number) {
		validate(number);
		return CACHE.get(number);
	}

	public int getLottoNumber() {
		return lottoNumber;
	}

	@Override
	public int compareTo(LottoNumber other) {
		return Integer.compare(lottoNumber, other.lottoNumber);
	}
}
