package lotto.domain;

public class InvalidMoneyAmountException extends RuntimeException {
    public InvalidMoneyAmountException(String message) {
        super(message);
    }
}
