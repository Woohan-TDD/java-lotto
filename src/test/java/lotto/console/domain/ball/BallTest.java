package lotto.console.domain.ball;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class BallTest {

	@DisplayName("로또 볼이 올바르게 생성되는지 확인하는 테스트")
	@Test
	void createCorrectBall() {
		Ball ball = new Ball(1);
		assertThat(ball).isNotNull();
	}

	@DisplayName("로또볼이 1 ~ 45가 아닌 수가 생성되면 예외처리")
	@ParameterizedTest
	@ValueSource(ints = {0, 46})
	void validateBetweenOneAndFortyFive(int ballNumber) {
		assertThatThrownBy(() -> new Ball(ballNumber)).isInstanceOf(IllegalArgumentException.class)
			.hasMessage("로또 볼은 1 ~ 45까지만 가능합니다. number = " + ballNumber);
	}
}