package lotto.console.controller;

import java.util.Collections;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import lotto.console.converter.Converter;
import lotto.console.domain.ball.BallFactory;
import lotto.console.domain.ball.WinningBalls;
import lotto.console.domain.ball.LottoTicket;
import lotto.console.domain.user.CreationCount;
import lotto.console.domain.user.Money;
import lotto.console.domain.user.Rank;
import lotto.console.domain.user.Result;
import lotto.console.domain.user.User;
import lotto.console.view.InputView;
import lotto.console.view.OutputView;

public class LottoController {

	private final static int MIN_LOTTO_TICKET_COUNT = 0;

	private final Logger logger = Logger.getLogger("logger");

	public void run() {
		try {
			User user = createUser();
			OutputView.printLottoTicket(user.getLottoTickets(), user.getCreationCount());
			WinningBalls winningBalls = createWinningBalls();
			List<Rank> ranks = user.calculateRanks(winningBalls);
			Result result = new Result(user.getMoney(), ranks);
			OutputView.printResult(result);
		} catch (IllegalArgumentException e) {
			logger.warning(e.getMessage());
		}
	}

	private User createUser() {
		Money money = createMoney();
		CreationCount creationCount = createCreationCount(money);

		return new User(money, creationCount, Stream.concat(
			createAutomaticLottoTickets(creationCount.getAutomaticCount()).stream(),
			createManualLottoTickets(creationCount.getManualCount()).stream()
		).collect(Collectors.toList()));
	}

	private Money createMoney() {
		String inputMoney = InputView.inputMoney();

		return new Money(Converter.numberConverterBy(inputMoney));
	}

	private CreationCount createCreationCount(final Money money) {
		String inputManualCount = InputView.inputManualCount();
		return new CreationCount(money, Converter.numberConverterBy(inputManualCount));
	}

	private List<LottoTicket> createAutomaticLottoTickets(final int automaticCount) {
		return Collections.unmodifiableList(IntStream.range(MIN_LOTTO_TICKET_COUNT, automaticCount)
			.mapToObj(i -> BallFactory.createRandomSixBalls())
			.collect(Collectors.toList()));
	}

	private List<LottoTicket> createManualLottoTickets(final int manualCount) {
		OutputView.printInputManualBalls();

		List<String> manualLottoBallNumbers = IntStream.range(MIN_LOTTO_TICKET_COUNT, manualCount)
			.mapToObj(i -> InputView.inputManualNumber())
			.collect(Collectors.toList());

		return Collections.unmodifiableList(manualLottoBallNumbers
			.stream()
			.map(Converter::numberListConverterBy)
			.map(BallFactory::createManualSixBalls)
			.collect(Collectors.toList()));
	}

	private WinningBalls createWinningBalls() {
		String winningBallNumbers = InputView.inputWinningBalls();
		String bonusBall = InputView.inputBonusBall();

		return new WinningBalls(createWinningBallsBy(winningBallNumbers),
			BallFactory.of(Converter.numberConverterBy(bonusBall)));
	}

	private LottoTicket createWinningBallsBy(final String winningBallNumbers) {
		return BallFactory.createManualSixBalls(
			Converter.numberListConverterBy(winningBallNumbers)
		);
	}
}