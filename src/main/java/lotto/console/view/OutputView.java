package lotto.console.view;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import lotto.console.domain.ball.Ball;
import lotto.console.domain.ball.LottoTicket;
import lotto.console.domain.user.CreationCount;
import lotto.console.domain.user.Rank;
import lotto.console.domain.user.Result;

public class OutputView {

	private static final String BALL_JOINING_DELIMITER = ", ";

	public static void printInputManualBalls() {
		System.out.println("수동으로 구매할 번호를 입력해 주세요.");
	}

	public static void printLottoTicket(final List<LottoTicket> lottoTickets, final CreationCount creationCount) {
		System.out.printf("수동으로 %d장, 자동으로 %d개를 구매했습니다.\n",
			creationCount.getManualCount(), creationCount.getAutomaticCount());

		for (LottoTicket lottoTicket : lottoTickets) {
			System.out.println(joinLottoTicketWithBracket(lottoTicket));
		}
	}

	public static void printResult(final Result result) {
		for (Rank rank : Rank.values()) {
			System.out.println(joinRankInfo(rank, result.sizeBy(rank)));
		}
		System.out.printf("총 수익률은 %f%%입니다.", result.calculateEarningRate());
	}

	private static String joinRankInfo(final Rank rank, final long rankPersonnel) {
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append(rank.getWinningCount())
			.append("개 일치 (")
			.append(rank.getWinningMoney())
			.append("원) - ")
			.append(rankPersonnel)
			.append("개");

		return stringBuilder.toString();
	}

	private static String joinLottoTicketWithBracket(final LottoTicket lottoTicket) {
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append("[")
			.append(joinLottoBalls(lottoTicket))
			.append("]");

		return stringBuilder.toString();
	}

	private static String joinLottoBalls(final LottoTicket lottoTicket) {
		return lottoTicket.getBalls().stream()
			.map(Ball::toString)
			.collect(Collectors.joining(BALL_JOINING_DELIMITER));
	}
}