package lotto.console.domain.ball;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class WinningBalls {

	private final LottoTicket winningBalls;
	private final Ball bonusBall;

	public WinningBalls(final LottoTicket winningBalls, final Ball bonusBall) {
		Objects.requireNonNull(winningBalls);
		Objects.requireNonNull(bonusBall);
		this.winningBalls = winningBalls;
		validateWinningBallsContainsBonusBall(bonusBall);
		this.bonusBall = bonusBall;
	}

	private void validateWinningBallsContainsBonusBall(Ball bonusBall) {
		if (winningBalls.isContains(bonusBall)) {
			throw new IllegalArgumentException("보너스 볼과 당첨 번호가 중복될 수 없습니다.");
		}
	}

	public List<Ball> getWinningBalls() {
		return Collections.unmodifiableList(winningBalls.getBalls());
	}

	public Ball getBonusBall() {
		return bonusBall;
	}
}