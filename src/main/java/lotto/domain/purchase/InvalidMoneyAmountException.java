package lotto.domain.purchase;

public class InvalidMoneyAmountException extends RuntimeException {
    public InvalidMoneyAmountException(String message) {
        super(message);
    }
}
