package domain.amount;

import java.util.Objects;

import domain.amount.element.ManualLottoAmount;

public class LottoMoney {
	private static final int LOTTO_PRICE = 1000;
	private static final int PERCENT = 100;

	private final long amount;

	public LottoMoney(long amount) {
		validate(amount);
		this.amount = amount;
	}

	private void validate(long amount) {
		if (amount < LOTTO_PRICE) {
			throw new IllegalArgumentException("로또의 금액은 1000원 이상이 필요합니다. amount=" + amount);
		}
	}

	public boolean isExcessLottoAmount(int amount) {
		return calculateLottoAmount() < amount;
	}

	public int calculateRemainderAmount(ManualLottoAmount manualLottoAmount) {
		Objects.requireNonNull(manualLottoAmount);

		return manualLottoAmount.minus(calculateLottoAmount()) * -1;
	}

	private int calculateLottoAmount() {
		return (int)(amount / LOTTO_PRICE);
	}

	public double calculateProfit(long winningMoney) {
		long revenue = winningMoney - amount;
		return ((double)revenue / amount) * PERCENT;
	}
}
