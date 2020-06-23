package lotto.domain.lottery;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class MatchResultTest {
    @DisplayName("결과를 입력받아 인스턴스 생성")
    @Test
    void constructor() {
        Map<Rank, Long> matchCount = new HashMap<>();
        matchCount.put(Rank.FIRST, 2L);

        assertThat(new MatchResult(matchCount)).isInstanceOf(MatchResult.class);
    }

    @DisplayName("생성자에 입력받은 인자가 null일때 예외 발생")
    @Test
    void constructor_MatchResultIsNull_ExceptionThrown() {
        assertThatThrownBy(() -> new MatchResult(null))
                .isInstanceOf(NullPointerException.class)
                .hasMessageContaining("결과가 존재하지 않습니다");
    }

    @DisplayName("구입 금액을 입력받아 수익률 계산")
    @Test
    void calculateProfitRate() {
        Map<Rank, Long> matchCount = new HashMap<>();
        matchCount.put(Rank.FIRST, 2L);
        MatchResult matchResult = new MatchResult(matchCount);

        long profitRate = matchResult.calculateProfitRate(10_000);

        assertThat(profitRate).isEqualTo(40_000_000L);
    }
}