package domain.store;

import java.util.Objects;

public class Money {
	private final long money;

	public Money(long money) {
		validate(money);
		this.money = money;
	}

	private void validate(long money) {
		if (money <= 0) {
			throw new IllegalArgumentException("0보다 큰 금액이어야 합니다. input : " + money);
		}
	}

	public double calculateRate(long winning) {
		return winning / money;
	}

	public boolean isDividable(long number) {
		return money % number == 0;
	}

	public int divideBy(long number) {
		return (int)(money / number);
	}

	public boolean isEqualToOrLessThan(long number) {
		return money <= number;
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
