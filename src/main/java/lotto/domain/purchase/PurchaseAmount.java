package lotto.domain.purchase;

public class PurchaseAmount {
    private static final int MIN_PURCHASE_AMOUNT = 0;

    private final int amount;

    public PurchaseAmount(final int amount) {
        validateAmount(amount);
        this.amount = amount;
    }

    private void validateAmount(int amount) {
        if (amount < MIN_PURCHASE_AMOUNT) {
            throw new InvalidPurchaseAmountException("구입할 금액이 올바르지 않습니다: " + amount);
        }
    }

    public int getAmount() {
        return amount;
    }
}
