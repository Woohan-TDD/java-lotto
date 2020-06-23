package lotto.domain.lottery;

public class InvalidLottoNumberException extends RuntimeException {
    public InvalidLottoNumberException(String message) {
        super(message);
    }
}
