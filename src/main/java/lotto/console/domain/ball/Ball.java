package lotto.console.domain.ball;

public class Ball {

	protected static final int MIN_BALL_NUMBER = 1;
	protected static final int MAX_BALL_NUMBER = 45;

	private final int number;

	protected Ball(final int number) {
		validateBetweenOneAndFortyFive(number);
		this.number = number;
	}

	public void validateBetweenOneAndFortyFive(final int number) {
		if (number < MIN_BALL_NUMBER || number > MAX_BALL_NUMBER) {
			throw new IllegalArgumentException("로또 볼은 1 ~ 45까지만 가능합니다. number = " + number);
		}
	}

	public boolean isNumber(final int ballNumber) {
		return this.number == ballNumber;
	}

	@Override
	public String toString() {
		return "number=" + number;
	}
}