package lotto.domain.purchase;

public class InvalidMoneyAmountException extends RuntimeException {
    public InvalidMoneyAmountException(final String message) {
        super(message);
    }
}
