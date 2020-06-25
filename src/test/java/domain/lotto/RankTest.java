package domain.lotto;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import domain.statistics.Rank;

class RankTest {
	@DisplayName("match와 bonus여부로 Rank의 값을 추출한다.")
	@CsvSource(value = {"6,false,SIX", "5,true,FIVE_BONUS", "5,false,FIVE", "4, false,FOUR", "4, true,FOUR",
		"3,false,THREE", "3,true,THREE", "2,false,NONE_MATCH", "1,false,NONE_MATCH", "0,false,NONE_MATCH"})
	@ParameterizedTest
	void name(int matched, boolean bonusMatch, Rank expect) {
		assertThat(Rank.of(matched, bonusMatch)).isEqualTo(expect);
	}

	@DisplayName("같은 Rank에 count 값을 곱한 결과 값을 구한다.")
	@CsvSource(value = {"THREE,1,5000", "FIVE,2,3000000", "SIX,1,2000000000"})
	@ParameterizedTest
	void name(Rank rank, int count, long expect) {
		assertThat(rank.multiplyMoneyByCount(count)).isEqualTo(expect);
	}
}