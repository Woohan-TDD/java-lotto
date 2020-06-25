package lotto.console.domain.user;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class CreationCountTest {

	@DisplayName("횟수가 잘 성되는지 확인하는 테스트")
	@Test
	void createCorrectAutomaticCountCreationCount() {
		CreationCount creationCount = new CreationCount(new Money(5000), 3);
		assertAll(
			() -> assertThat(creationCount.getTotalCount()).isEqualTo(5),
			() -> assertThat(creationCount.getAutomaticCount()).isEqualTo(2),
			() -> assertThat(creationCount.getManualCount()).isEqualTo(3)
		);
	}

	@DisplayName("총 횟수 보다 수동 횟수 입력값이 클 경우 예외처리")
	@Test
	void validateGreaterManualCountThanTotalCount() {
		assertThatThrownBy(() -> new CreationCount(new Money(5000), 10))
			.isInstanceOf(IllegalArgumentException.class)
			.hasMessage("총 횟수에 비해 수동 입력값이 큽니다. totalCount = 5, manualCount = 10");
	}

	@DisplayName("수동 입력 횟수가 음수일 경우 예외처리")
	@Test
	void validateNegativeManualCount() {
		assertThatThrownBy(() -> new CreationCount(new Money(5000), -1))
			.isInstanceOf(IllegalArgumentException.class)
			.hasMessage("수동 구매 입력 횟수는 음수가 될 수 없습니다. manualCount = -1");
	}
}