package lotto.domain.purchase;

public class InvalidPurchaseAmountException extends RuntimeException {
    public InvalidPurchaseAmountException(final String message) {
        super(message);
    }
}
