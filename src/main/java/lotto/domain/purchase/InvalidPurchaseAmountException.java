package lotto.domain.purchase;

public class InvalidPurchaseAmountException extends RuntimeException {
    public InvalidPurchaseAmountException(String message) {
        super(message);
    }
}
