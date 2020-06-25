package domain.amount.element;

public abstract class Amount {
	private final int amount;

	Amount(int amount) {
		validate(amount);
		this.amount = amount;
	}

	private void validate(int amount) {
		if (amount < 0) {
			throw new IllegalArgumentException("수량은 음수를 입력할 수 없습니다. amount=" + amount);
		}
	}

	public boolean isExcess(int amount) {
		return this.amount > amount;
	}

	public int minus(int other) {
		return amount - other;
	}

	public int getAmount() {
		return amount;
	}
}
