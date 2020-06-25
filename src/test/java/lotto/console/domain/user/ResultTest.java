package lotto.console.domain.user;

import static org.assertj.core.api.Assertions.*;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class ResultTest {

	@DisplayName("수익률을 구하는 테스트")
	@Test
	void calculateEarningRate() {
		Money money = new Money(5000);
		List<Rank> ranks = Arrays.asList(Rank.FIRST, Rank.SECOND, Rank.NO, Rank.NO, Rank.NO);
		Result result = new Result(money, ranks);
		assertThat(result.calculateEarningRate()).isEqualTo(40599900);
	}

	@DisplayName("각 순위의 갯수 구하기")
	@ParameterizedTest
	@CsvSource(value = {
		"FIRST:0", "SECOND:1", "THIRD:2", "FOURTH:0", "FIFTH:1", "NO:3"
	}, delimiter = ':')
	void ㄱ(Rank rank, int size) {
		List<Rank> ranks = Arrays.asList(
			Rank.SECOND, Rank.THIRD, Rank.THIRD, Rank.FIFTH, Rank.NO, Rank.NO, Rank.NO
		);

		Result result = new Result(new Money(7000), ranks);

		assertThat(result.sizeBy(rank)).isEqualTo(size);
	}
}