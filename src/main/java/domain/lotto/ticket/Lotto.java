package domain.lotto.ticket;

import static java.util.stream.Collectors.*;

import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public class Lotto {
	public static final int SIZE = 6;

	private final Set<LottoNumber> lotto;

	private Lotto(Set<LottoNumber> lotto) {
		validateSize(lotto);
		this.lotto = lotto;
	}

	private void validateSize(Set<LottoNumber> lotto) {
		if (lotto.size() != SIZE) {
			throw new IllegalArgumentException("로또 숫자는 " + SIZE + "개여야 합니다. size : " + lotto.size());
		}
	}

	public static Lotto valueOf(List<Integer> lotto) {
		return lotto.stream()
			.map(LottoNumber::of)
			.collect(collectingAndThen(toCollection(TreeSet::new), Lotto::new));
	}

	public boolean contains(LottoNumber lottoNumber) {
		return lotto.contains(lottoNumber);
	}

	public int countMatchedNumber(Lotto compared) {
		return (int)lotto.stream()
			.filter(compared::contains)
			.count();
	}

	public Set<LottoNumber> getLotto() {
		return Collections.unmodifiableSet(lotto);
	}
}
