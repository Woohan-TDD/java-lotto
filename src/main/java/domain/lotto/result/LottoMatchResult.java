package domain.lotto.result;

import java.util.Objects;

import domain.lotto.ticket.Lotto;

public class LottoMatchResult {
	private final int matchCount;
	private final boolean bonus;

	public LottoMatchResult(int matchCount, boolean bonus) {
		validateMatchCount(matchCount);
		this.matchCount = matchCount;
		this.bonus = bonus;
	}

	private void validateMatchCount(int matchCount) {
		if (matchCount < 0) {
			throw new IllegalArgumentException("matchCount는 음수가 불가능합니다. input : " + matchCount);
		}
		if (matchCount > Lotto.SIZE) {
			throw new IllegalArgumentException("matchCount는 로또 크기를 초과할 수 없습니다. input : "+ matchCount);
		}
	}

	public boolean hasBonus() {
		return bonus;
	}

	public boolean isEqualToMatchCount(int count) {
		return matchCount == count;
	}

	public boolean isEqualToOrGraterThanMatchCount(int count) {
		return matchCount <= count;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		LottoMatchResult that = (LottoMatchResult)o;
		return matchCount == that.matchCount &&
			bonus == that.bonus;
	}

	@Override
	public int hashCode() {
		return Objects.hash(matchCount, bonus);
	}
}
