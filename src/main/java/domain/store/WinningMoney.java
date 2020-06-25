package domain.store;

import java.util.Objects;

public class WinningMoney {
	private static final int PERCENT = 100;

	private final long winning;

	public WinningMoney(long winning) {
		validate(winning);
		this.winning = winning;
	}

	private void validate(long winning) {
		if (winning < 0) {
			throw new IllegalArgumentException("총 당첨 금액은 음수일 수 없습니다. input : " + winning);
		}
	}

	public static WinningMoney sum(WinningMoney firstOperand, WinningMoney secondOperand) {
		return new WinningMoney(firstOperand.winning + secondOperand.winning);
	}

	public WinningMoney add(Long newWinning) {
		return new WinningMoney(winning + newWinning);
	}

	public int calculateWinningRate(Money money) {
		return (int)(money.calculateRate(winning) * PERCENT);
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		WinningMoney that = (WinningMoney)o;
		return winning == that.winning;
	}

	@Override
	public int hashCode() {
		return Objects.hash(winning);
	}
}
