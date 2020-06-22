package lotto.domain;

public class Money {
    private static final int MONEY_UNIT = 1000;

    private final int amount;

    public Money(final int amount) {
        validateAmount(amount);
        this.amount = amount;
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
        if (amount <= 0) {
            throw new InvalidMoneyAmountException("입력한 금액이 양수가 아닙니다: " + amount);
        }
    }

    public int getAmount() {
        return amount;
    }
}
