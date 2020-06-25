package lotto.console.domain.ball;

import static java.util.stream.Collectors.*;
import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.stream.IntStream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class WinningBallsTest {
	private LottoTicket lottoTicket;

	@BeforeEach
	void setUp() {
		 lottoTicket = IntStream.range(1, 7)
			.mapToObj(BallFactory::of)
			.collect(collectingAndThen(toList(), LottoTicket::new));
	}

	@DisplayName("당첨 번호가 제대로 생성되는지 확인하는 테스트")
	@Test
	void createCorrectWinningBalls() {
		Ball bonusBall = BallFactory.of(7);
		WinningBalls winningBalls = new WinningBalls(lottoTicket, bonusBall);

		assertAll(
			() -> assertThat(winningBalls.getWinningBalls()).containsAll(lottoTicket.getBalls()),
			() -> assertThat(winningBalls.getBonusBall()).isEqualTo(bonusBall)
		);
	}

	@DisplayName("당첨 번호와 보너스 볼이 중복될 경우 예외처리")
	void validateWinningBallsContainsBonusBall(){
		assertThatThrownBy(() -> new WinningBalls(lottoTicket, BallFactory.of(6)))
		.isInstanceOf(IllegalArgumentException.class)
		.hasMessage("보너스 볼과 당첨 번호가 중복될 수 없습니다.");
	}
}