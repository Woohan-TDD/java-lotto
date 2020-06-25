package lotto.console.domain.ticket;

import static org.assertj.core.api.Assertions.*;

import java.util.stream.Collectors;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import lotto.console.converter.Converter;
import lotto.console.domain.ball.BallFactory;
import lotto.console.domain.ball.LottoTicket;

public class LottoTicketTest {

	@DisplayName("6개의 로또 볼이 들어있는 로또 티켓이 생성되는지 확인하는 테스트")
	@Test
	void createSixBall() {
		LottoTicket lottoTicket = BallFactory.createRandomSixBalls();
		assertThat(lottoTicket.getBalls()).hasSize(6);
	}

	@DisplayName("수동 로또 번호 입력 값 중에 중복이 있을 경우 예외처리")
	@Test
	void validateBallNumbersReduplication() {
		assertThatThrownBy(() -> new LottoTicket(
			Converter.numberListConverterBy("1,1,2,3,4,5")
				.stream()
				.map(BallFactory::of)
				.collect(Collectors.toList()))).isInstanceOf(IllegalArgumentException.class)
			.hasMessageContaining("중복된 로또 번호를 선택 했습니다. ballNumbers = ");
	}

	@DisplayName("수동 로또 볼 입력 값이 6개가 아닌 경우 예외처리")
	@ParameterizedTest
	@ValueSource(strings = {"1,2,3,4,5", "1,2,3,4,5,6,7"})
	void validateSizeNotSix(String ballNumbers) {
		assertThatThrownBy(() -> new LottoTicket(
			Converter.numberListConverterBy((ballNumbers))
				.stream()
				.map(BallFactory::of)
				.collect(Collectors.toList()))
		).isInstanceOf(IllegalArgumentException.class)
			.hasMessageContaining("로또 한장 당 6개씩 가질 수 습니다. ballsSize = ");
	}
}