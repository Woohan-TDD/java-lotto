package lotto.domain.purchase;

public class LackOfMoneyException extends RuntimeException {
    public LackOfMoneyException(final String message) {
        super(message);
    }
}
