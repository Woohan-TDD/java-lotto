package domain.lotto;

import java.util.Objects;

import domain.lotto.number.LottoNumber;

public class WinningLotto {
	private final Lotto lotto;
	private final LottoNumber bonusNumber;

	public WinningLotto(Lotto lotto, LottoNumber lottoNumber) {
		validate(lotto, lottoNumber);
		this.lotto = lotto;
		this.bonusNumber = lottoNumber;
	}

	private void validate(Lotto lotto, LottoNumber lottoNumber) {
		Objects.requireNonNull(lotto);
		Objects.requireNonNull(lottoNumber);

		if (lotto.contains(lottoNumber)) {
			throw new IllegalArgumentException(
				"당첨번호와 보너스번호는 중복될 수 없습니다. domain.lotto=" + lotto + "lottoNumber" + lottoNumber);
		}
	}

	public int countMatch(Lotto lotto) {
		return this.lotto.countMatch(lotto);
	}

	public boolean checkBonus(Lotto lotto) {
		return lotto.contains(this.bonusNumber);
	}

	@Override
	public String toString() {
		return "WinningLotto{" +
			"domain.lotto=" + lotto +
			", bonusNumber=" + bonusNumber +
			'}';
	}
}
