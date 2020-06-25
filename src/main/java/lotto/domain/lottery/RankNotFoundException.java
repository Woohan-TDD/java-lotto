package lotto.domain.lottery;

public class RankNotFoundException extends RuntimeException {
    public RankNotFoundException(final String message) {
        super(message);
    }
}
