package domain.purchase;

import domain.WinningResult;

public class Money {
    private static final int MIN_MONEY = 1000;

    private final int money;

    public Money(final int money) {
        validateMoney(money);
        this.money = money;
    }

    private void validateMoney(int money) {
        if (money < MIN_MONEY) {
            throw new IllegalArgumentException("1000원 이상의 금액을 입력 해 주세요.");
        }
        if (money % MIN_MONEY != 0) {
            throw new IllegalArgumentException("1000원 단위로 입력하세요.");
        }
    }

    public int calculateCount() {
        return this.money / MIN_MONEY;
    }

    public double calculateWinningRate(WinningResult winningResult) {
        int winningMoney = winningResult.calculateWinningMoney();
        return (double) (winningMoney - money) * 100 / money;
    }
}
