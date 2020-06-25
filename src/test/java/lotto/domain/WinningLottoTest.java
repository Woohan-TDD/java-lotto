package lotto.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class WinningLottoTest {

    private LottoTicket lottoTicket;

    @BeforeEach
    void setUp() {
        lottoTicket = LottoTicketUtil.getLottoTicket(1);
    }

    @Test
    void validate_BonusBall() {
        String input = "1, 2, 3, 4, 5, 6";
        int bonusBall = 2;
        assertThatThrownBy(() -> WinningLotto.of(input, bonusBall))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void getMatchCount() {
        WinningLotto winningLotto = WinningLotto.of("4,5,6,7,8,9", 10);
        assertThat(winningLotto.getMatchCount(lottoTicket)).isEqualTo(3);
    }

    @Test
    void hasBonusByLottoTicket_True() {
        WinningLotto winningLotto = WinningLotto.of("1,2,3,4,5,7", 6);
        assertThat(winningLotto.hasBonusByLottoTicket(lottoTicket)).isTrue();
    }


    @Test
    void hasBonusByLottoTicket_False() {
        WinningLotto winningLotto = WinningLotto.of("1,2,3,4,5,7", 8);
        assertThat(winningLotto.hasBonusByLottoTicket(lottoTicket)).isFalse();
    }
}