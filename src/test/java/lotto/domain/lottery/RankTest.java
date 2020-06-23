package lotto.domain.lottery;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class RankTest {
    @DisplayName("6개의 수가 모두 일치하는 경우 FIRST 반환")
    @Test
    void match_SixNumberMatch_First() {
        assertThat(Rank.match(6, false)).isEqualTo(Rank.FIRST);
    }

    @DisplayName("5개의 수가 일치하고, 보너스 번호가 일치하는 경우 SECOND 반환")
    @Test
    void match_FiveNumberMatchAndBonusMatch_Second() {
        assertThat(Rank.match(5, true)).isEqualTo(Rank.SECOND);

    }

    @DisplayName("5개의 수가 일치하고, 보너스 번호가 일치하지 않는 경우 THIRD 반환")
    @Test
    void match_FiveNumberMatch_Third() {
        assertThat(Rank.match(5, false)).isEqualTo(Rank.THIRD);
    }

    @DisplayName("4개의 수가 일치하는 경우 경우 FOURTH 반환")
    @ValueSource(booleans = {true, false})
    @ParameterizedTest
    void match_FourNumberMatch_Fourth(final boolean isBonus) {
        assertThat(Rank.match(4, isBonus)).isEqualTo(Rank.FOURTH);
    }

    @DisplayName("3개의 수가 일치하는 경우 경우 FOURTH 반환")
    @ValueSource(booleans = {true, false})
    @ParameterizedTest
    void match_ThreeNumberMatch_Fifth(final boolean isBonus) {
        assertThat(Rank.match(3, isBonus)).isEqualTo(Rank.FIFTH);
    }

    @DisplayName("3개 미만의 수가 일치하는 경우 경우 NOTHING 반환")
    @ValueSource(booleans = {true, false})
    @ParameterizedTest
    void match_LessThanThreeNumberMatch_Nothing(final boolean isBonus) {
        assertThat(Rank.match(2, isBonus)).isEqualTo(Rank.NOTHING);
    }

    @DisplayName("당첨 개수를 입력받아 총 당첨액 계산")
    @Test
    void calculateTotalPrize() {
        assertThat(Rank.FIRST.calculateTotalPrize(15)).isEqualTo(30_000_000_000L);
    }
}
