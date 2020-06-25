package lotto.console.domain.ball;

import java.util.Collections;
import java.util.List;

import lotto.console.domain.ball.Ball;

public class LottoTicket {

	protected final static int LOTTO_TICKET_SIZE = 6;

	private final List<Ball> balls;

	public LottoTicket(final List<Ball> balls) {
		validate(balls);
		this.balls = balls;
	}

	public boolean isContains(Ball ball){
		return balls.contains(ball);
	}

	private void validate(final List<Ball> balls) {
		validateOverSix(balls);
		validateBallNumbersReduplication(balls);
		validateBallNumbersSizeNotSix(balls);
	}

	private void validateOverSix(final List<Ball> balls) {
		if (balls.size() != LOTTO_TICKET_SIZE) {
			throw new IllegalArgumentException("로또 한장 당 6개씩 가질 수 습니다. ballsSize = " + balls.size());
		}
	}

	private void validateBallNumbersSizeNotSix(final List<Ball> balls) {
		if (balls.size() != LOTTO_TICKET_SIZE) {
			throw new IllegalArgumentException("로또 번호가 6개가 아닙니다. ballNumbers = " + balls);
		}
	}

	private void validateBallNumbersReduplication(final List<Ball> balls) {
		long ballNumberSize = balls.stream()
			.distinct()
			.count();

		if (ballNumberSize != LOTTO_TICKET_SIZE) {
			throw new IllegalArgumentException("중복된 로또 번호를 선택 했습니다. ballNumbers = " + balls);
		}
	}

	public List<Ball> getBalls() {
		return Collections.unmodifiableList(balls);
	}
}