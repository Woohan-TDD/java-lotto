package lotto.domain.lottery;

public class InvalidLottoNumberException extends RuntimeException {
    public InvalidLottoNumberException(final String message) {
        super(message);
    }
}
