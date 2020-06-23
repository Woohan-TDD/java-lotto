package lotto.domain.purchase;

public class PurchaseAmount {
    private static final int MIN_PURCHASE_AMOUNT = 0;

    private final int amount;

    public PurchaseAmount(final int amount) {
        validateAmount(amount);
        this.amount = amount;
    }

    public boolean isNotSameAmount(final int amount) {
        return this.amount != amount;
    }

    private void validateAmount(int amount) {
        if (amount < MIN_PURCHASE_AMOUNT) {
            throw new InvalidPurchaseAmountException("구입할 개수가 올바르지 않습니다: " + amount);
        }
    }

    public int getAmount() {
        return amount;
    }
}
