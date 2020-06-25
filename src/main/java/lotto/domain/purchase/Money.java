package lotto.domain.purchase;

import java.util.Objects;

public class Money {
    private static final int MONEY_UNIT = 1000;
    private static final int MIN_MONEY_AMOUNT = 0;

    private int amount;

    public Money(final int amount) {
        validateAmount(amount);
        this.amount = amount;
    }

    public void spend(final Money spendMoney) {
        validateMoneyAvailability(spendMoney);
        this.amount -= spendMoney.amount;
    }

    public boolean hasAvailableAmount() {
        return amount != MIN_MONEY_AMOUNT;
    }

    private void validateMoneyAvailability(final Money spendMoney) {
        if (this.amount < spendMoney.amount) {
            throw new LackOfMoneyException("금액이 부족합니다. " +
                    "보유금액: " + this.amount + ", " +
                    "소모할 금액: " + spendMoney.amount
            );
        }
    }

    private void validateAmount(final int amount) {
        validateAmountRange(amount);
        validateAmountUnit(amount);
    }

    private void validateAmountUnit(final int amount) {
        if (amount % MONEY_UNIT != 0) {
            throw new InvalidMoneyAmountException("입력한 금액이 1,000 단위로 나누어 떨어지지 않습니다: " + amount);
        }
    }

    private void validateAmountRange(final int amount) {
        if (amount < MIN_MONEY_AMOUNT) {
            throw new InvalidMoneyAmountException("입력한 금액이 양수가 아닙니다: " + amount);
        }
    }

    public int getAmount() {
        return amount;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        final Money money = (Money)o;
        return amount == money.amount;
    }

    @Override
    public int hashCode() {
        return Objects.hash(amount);
    }
}
