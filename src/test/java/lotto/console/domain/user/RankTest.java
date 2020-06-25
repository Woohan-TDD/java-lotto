package lotto.console.domain.user;

import static org.assertj.core.api.Assertions.*;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import lotto.console.converter.Converter;
import lotto.console.domain.ball.BallFactory;
import lotto.console.domain.ball.WinningBalls;
import lotto.console.domain.ball.LottoTicket;

public class RankTest {

	@DisplayName("각 등수를 확인하는 테스트")
	@ParameterizedTest
	@CsvSource(value = {
		"1,2,3,4,5,6:FIRST", "1,2,3,4,5,7:SECOND", "1,2,3,4,5,10:THIRD",
		"1,2,3,4,8,9:FOURTH", "1,2,3,10,11,12:FIFTH", "1,2,10,11,12,13:NO",
		"1,10,11,12,13,14:NO", "10,11,12,13,14,15:NO"
	}, delimiter = ':')
	void findRank(String lottoTicketNumbers, Rank rank) {
		List<Integer> winningBallNumbers = IntStream.range(1, 7)
			.boxed()
			.collect(Collectors.toList());

		WinningBalls winningBalls = new WinningBalls(
			BallFactory.createManualSixBalls(winningBallNumbers),
			BallFactory.of(7));

		LottoTicket lottoTicket = BallFactory.createManualSixBalls(
			Converter.numberListConverterBy(lottoTicketNumbers)
		);

		assertThat(Rank.of(lottoTicket, winningBalls)).isEqualTo(rank);
	}
}