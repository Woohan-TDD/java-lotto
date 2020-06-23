package lotto;

import java.util.Objects;

public class Money {
	private long money;

	public Money(long money) {
		validate(money);
		this.money = money;
	}

	private void validate(long money) {
		if (money <= 0) {
			throw new IllegalArgumentException("0보다 큰 금액이어야 합니다.");
		}
	}

	public double calculateRate(long winning) {
		return winning / money;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		Money money1 = (Money)o;
		return money == money1.money;
	}

	@Override
	public int hashCode() {
		return Objects.hash(money);
	}
}
