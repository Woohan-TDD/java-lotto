package domain.lotto;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

import domain.lotto.number.LottoNumber;

public class Lotto {
	public static final int SIZE = 6;

	private final List<LottoNumber> lottoNumbers;

	public Lotto(List<LottoNumber> lottoNumbers) {
		validate(lottoNumbers);

		this.lottoNumbers = new ArrayList<>(lottoNumbers);
		Collections.sort(this.lottoNumbers);
	}

	private void validate(List<LottoNumber> lottoNumbers) {
		Objects.requireNonNull(lottoNumbers);
		validateSize(lottoNumbers);
		validateDuplicate(lottoNumbers);
	}

	private void validateSize(List<LottoNumber> lottoNumbers) {
		if (lottoNumbers.size() != SIZE) {
			throw new IllegalArgumentException("로또는 로또넘버 6개로 만들 수 있습니다. lottoNumbers=" + lottoNumbers);
		}
	}

	private void validateDuplicate(List<LottoNumber> lottoNumbers) {
		long count = lottoNumbers.stream()
			.distinct()
			.count();

		if (count != SIZE) {
			throw new IllegalArgumentException("로또 번호는 중복될 수 없습니다." + lottoNumbers);
		}
	}

	public boolean contains(LottoNumber lottoNumber) {
		Objects.requireNonNull(lottoNumber);
		return lottoNumbers.contains(lottoNumber);
	}

	public int countMatch(Lotto other) {
		Objects.requireNonNull(other);
		return (int)lottoNumbers.stream()
			.filter(other::contains)
			.count();
	}

	@Override
	public String toString() {
		return "Lotto{" +
			"lottoNumbers=" + lottoNumbers +
			'}';
	}

	public List<LottoNumber> getLottoNumbers() {
		return Collections.unmodifiableList(lottoNumbers);
	}
}
