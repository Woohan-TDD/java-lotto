package lotto.domain.lottery;

import static lotto.domain.lottery.LottoTicketUtil.generateLottoTicket;
import static lotto.domain.lottery.LottoTicketUtil.generateLottoTickets;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class LottoTicketsTest {
    @DisplayName("여러장의 로또 티켓 생성")
    @Test
    void constructor() {
        List<LottoTicket> lottoTickets = Arrays.asList(
                generateLottoTicket(1, 2, 3, 4, 5, 6),
                generateLottoTicket(1, 2, 3, 4, 5, 6),
                generateLottoTicket(1, 2, 3, 4, 5, 6)
        );

        assertThat(new LottoTickets(lottoTickets)).isInstanceOf(LottoTickets.class);
    }

    @DisplayName("입력한 로또 리스트가 null인 경우 예외 발생")
    @Test
    void constructor_LottoTicketListIsNull_ExceptionThrown() {
        assertThatThrownBy(() -> new LottoTickets(null))
                .isInstanceOf(NullPointerException.class)
                .hasMessageContaining("로또 리스트가 null입니다");
    }

    @DisplayName("로또 티켓 두 장을 연결")
    @Test
    void concat() {
        LottoTickets lottoTickets1 = generateLottoTickets(
                generateLottoTicket(1, 2, 3, 4, 5, 6)
        );
        LottoTickets lottoTickets2 = generateLottoTickets(
                generateLottoTicket(1, 2, 3, 4, 5, 6),
                generateLottoTicket(1, 2, 3, 4, 5, 6)
        );

        LottoTickets lottoTickets = lottoTickets1.concat(lottoTickets2);
        assertThat(lottoTickets.size()).isEqualTo(3);
    }

    @DisplayName("로또 티켓 리스트를 반환")
    @Test
    void getLottoTickets() {
        List<LottoTicket> tickets = Arrays.asList(
                generateLottoTicket(1, 2, 3, 4, 5, 6),
                generateLottoTicket(1, 2, 3, 4, 5, 6),
                generateLottoTicket(1, 2, 3, 4, 5, 6)
        );

        LottoTickets lottoTickets = new LottoTickets(tickets);
        assertThat(lottoTickets.getLottoTickets()).isEqualTo(tickets);
    }
}
