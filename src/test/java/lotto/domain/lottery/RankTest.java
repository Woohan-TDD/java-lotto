package lotto.domain.lottery;

import static lotto.domain.lottery.LottoTicketUtil.generateLottoTicket;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.stream.Stream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class RankTest {
    private WinningLotto winningLotto;

    private static Stream<Arguments> generateLottoTickets() {
        return Stream.of(
                Arguments.of(generateLottoTicket(1, 2, 7, 8, 9, 10)),
                Arguments.of(generateLottoTicket(1, 7, 8, 9, 10, 45)),
                Arguments.of(generateLottoTicket(7, 8, 9, 10, 11, 12))
        );
    }

    @BeforeEach
    void setUp() {
        LottoTicket lottoTicket = generateLottoTicket(1, 2, 3, 4, 5, 6);
        LottoNumber lottoNumber = LottoNumber.valueOf(45);
        winningLotto = new WinningLotto(lottoTicket, lottoNumber);
    }

    @DisplayName("6개의 수가 모두 일치하는 경우 FIRST 반환")
    @Test
    void match_SixNumberMatch_First() {
        LottoTicket lottoTicket = generateLottoTicket(1, 2, 3, 4, 5, 6);

        assertThat(Rank.match(winningLotto, lottoTicket)).isEqualTo(Rank.FIRST);
    }

    @DisplayName("5개의 수가 일치하고, 보너스 번호가 일치하는 경우 경우 SECOND 반환")
    @Test
    void match_FiveNumberMatchAndBonusMatch_Second() {
        LottoTicket lottoTicket = generateLottoTicket(1, 2, 3, 4, 5, 45);

        assertThat(Rank.match(winningLotto, lottoTicket)).isEqualTo(Rank.SECOND);
    }

    @DisplayName("5개의 수가 일치하는 경우 경우 THIRD 반환")
    @Test
    void match_FiveNumberMatch_Third() {
        LottoTicket lottoTicket = generateLottoTicket(1, 2, 3, 4, 5, 7);

        assertThat(Rank.match(winningLotto, lottoTicket)).isEqualTo(Rank.THIRD);
    }

    @DisplayName("4개의 수가 일치하는 경우 경우 FOURTH 반환")
    @Test
    void match_FourNumberMatch_Fourth() {
        LottoTicket lottoTicket = generateLottoTicket(1, 2, 3, 4, 7, 8);

        assertThat(Rank.match(winningLotto, lottoTicket)).isEqualTo(Rank.FOURTH);
    }

    @DisplayName("3개의 수가 일치하는 경우 경우 FOURTH 반환")
    @Test
    void match_ThreeNumberMatch_Fifth() {
        LottoTicket lottoTicket = generateLottoTicket(1, 2, 3, 7, 8, 9);

        assertThat(Rank.match(winningLotto, lottoTicket)).isEqualTo(Rank.FIFTH);
    }

    @DisplayName("3개 미만의 수가 일치하는 경우 경우 NOTHING 반환")
    @MethodSource("generateLottoTickets")
    @ParameterizedTest
    void match_LessThanThreeNumberMatch_Nothing(final LottoTicket lottoTicket) {
        assertThat(Rank.match(winningLotto, lottoTicket)).isEqualTo(Rank.NOTHING);
    }
}
