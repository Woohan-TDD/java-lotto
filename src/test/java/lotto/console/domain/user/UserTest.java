package lotto.console.domain.user;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import lotto.console.converter.Converter;
import lotto.console.domain.ball.BallFactory;
import lotto.console.domain.ball.WinningBalls;
import lotto.console.domain.ball.LottoTicket;

public class UserTest {

	@DisplayName("올바르게 유저가 생성되는지 확인하는 테스트")
	@Test
	void createCorrectUser() {
		Money money = new Money(10000);
		CreationCount creationCount = new CreationCount(money, 5);
		List<LottoTicket> lottoTickets = createLottoTickets();
		User user = new User(money, creationCount, lottoTickets);
		assertAll(
			() -> assertThat(user.getMoney()).isEqualTo(money),
			() -> assertThat(user.getCreationCount()).isEqualTo(creationCount),
			() -> assertThat(user.getLottoTickets()).containsAll(lottoTickets)
		);
	}

	@DisplayName("유저가 가지고 있는 로또 티켓의 당첨순위를 반환하는 테스트")
	@Test
	void calculateRank() {
		Money money = new Money(5000);
		User user = new User(money, new CreationCount(money, 5), createLottoTickets());
		WinningBalls winningBalls = createWinningBalls();

		assertThat(user.calculateRanks(winningBalls)).containsAll(Arrays.asList(
			Rank.FIRST, Rank.FIFTH, Rank.FIFTH, Rank.NO, Rank.NO
		));
	}

	private WinningBalls createWinningBalls() {
		LottoTicket winningBalls = BallFactory.createManualSixBalls(
			Converter.numberListConverterBy("1,2,3,4,5,6"));
		return new WinningBalls(winningBalls, BallFactory.of(7));
	}

	private List<LottoTicket> createLottoTickets() {
		List<String> lottoTicketNumbers = Arrays.asList(
			"1,2,3,4,5,6", "6,7,8,9,1,2", "4,5,6,7,8,9", "4,5,9,7,8,10", "4,10,1,7,8,9"
		);

		return lottoTicketNumbers.stream()
			.map(Converter::numberListConverterBy)
			.map(BallFactory::createManualSixBalls)
			.collect(Collectors.toList());
	}
}