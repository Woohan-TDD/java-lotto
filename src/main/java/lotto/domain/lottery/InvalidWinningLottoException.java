package lotto.domain.lottery;

public class InvalidWinningLottoException extends RuntimeException {
    public InvalidWinningLottoException(final String message) {
        super(message);
    }
}
