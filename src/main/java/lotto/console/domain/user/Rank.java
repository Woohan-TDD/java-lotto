package lotto.console.domain.user;

import java.util.Arrays;

import lotto.console.domain.ball.WinningBalls;
import lotto.console.domain.ball.LottoTicket;

public enum Rank {

	FIRST(2_000_000_000, 6),
	SECOND(30_000_000, 5),
	THIRD(1_500_000, 5),
	FOURTH(50_000, 4),
	FIFTH(5_000, 3),
	NO(0, 0);

	private final int winningMoney;
	private final int winningCount;

	Rank(final int winningMoney, final int winningCount) {
		this.winningMoney = winningMoney;
		this.winningCount = winningCount;
	}

	public static Rank of(final LottoTicket lottoTicket, final WinningBalls winningBalls) {
		long winningCount = winningBalls.getWinningBalls()
			.stream()
			.flatMap(ball -> lottoTicket.getBalls()
				.stream()
				.filter(ball::equals)).count();

		return Arrays.stream(Rank.values())
			.filter(rank -> rank.getWinningCount() == winningCount &&
				rank.isSecond(lottoTicket, winningBalls))
			.findFirst()
			.orElseGet(() -> Rank.NO);
	}

	private boolean isSecond(final LottoTicket lottoTicket, final WinningBalls winningBalls) {
		if (this != Rank.SECOND) {
			return true;
		}

		return lottoTicket.isContains(winningBalls.getBonusBall());
	}

	public int getWinningMoney() {
		return winningMoney;
	}

	public int getWinningCount() {
		return winningCount;
	}
}