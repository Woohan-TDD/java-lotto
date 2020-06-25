package lotto.console.domain.ball;

import static java.util.stream.Collectors.*;
import static lotto.console.domain.ball.Ball.*;
import static lotto.console.domain.ball.LottoTicket.*;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.IntStream;

public class BallFactory {

	private static final List<Ball> balls = IntStream.range(MIN_BALL_NUMBER, MAX_BALL_NUMBER + 1)
		.mapToObj(Ball::new)
		.collect(toList());

	private BallFactory() {
	}

	public static Ball of(final int ballNumber) {
		return balls.stream()
			.filter(ball -> ball.isNumber(ballNumber))
			.findFirst()
			.orElseThrow(() -> new IllegalArgumentException("로또 볼은 1 ~ 45까지만 있습니다. ballNumber = " + ballNumber));
	}

	public static LottoTicket createRandomSixBalls() {
		return ThreadLocalRandom.current()
			.ints(MIN_BALL_NUMBER, MAX_BALL_NUMBER).
				distinct()
			.limit(LOTTO_TICKET_SIZE)
			.sorted()
			.mapToObj(BallFactory::of)
			.collect(collectingAndThen(toList(), LottoTicket::new));
	}

	public static LottoTicket createManualSixBalls(final List<Integer> ballNumbers) {
		return ballNumbers.stream()
			.sorted()
			.map(BallFactory::of)
			.collect(collectingAndThen(toList(), LottoTicket::new));
	}
}