package lotto.console.domain.user;

public class Money {

	private static final int MONEY_UNIT = 1000;

	private final int money;

	public Money(final int money) {
		validateNegativeMoney(money);
		validateUnitThousandWon(money);
		this.money = money;
	}

	public int getMoney() {
		return money;
	}

	private void validateUnitThousandWon(final int money) {
		if (money % MONEY_UNIT != 0) {
			throw new IllegalArgumentException("천원 단위로만 입력이 가능합니다. money = " + money);
		}
	}

	private void validateNegativeMoney(final int money) {
		if (money < 0) {
			throw new IllegalArgumentException("금액은 음수가 될 수 없습니다. money = " + money);
		}
	}
}