package lotto.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

public class LottoTicketsTest {

    private List<LottoTicket> lottoTickets1;
    private List<LottoTicket> lottoTickets2;

    @BeforeEach
    void setUp() {
        LottoTicket lotto1 = LottoTicketUtil.getLottoTicket(1);
        LottoTicket lotto2 = LottoTicketUtil.getLottoTicket(2);
        LottoTicket lotto3 = LottoTicketUtil.getLottoTicket(3);
        LottoTicket lotto4 = LottoTicketUtil.getLottoTicket(4);

        lottoTickets1 = Arrays.asList(lotto1, lotto2);
        lottoTickets2 = Arrays.asList(lotto3, lotto4);
    }

    @Test
    void create() {
        LottoTickets tickets = LottoTickets.of(lottoTickets1, lottoTickets2);
        assertThat(tickets).isNotNull();
        assertThat(tickets.getLottoTickets()).hasSize(4);
    }

    @Test
    void matchCountAll() {
        WinningLotto winningLotto = WinningLotto.of("1, 2, 3, 4, 5, 6", 7);
        LottoTickets tickets = LottoTickets.of(lottoTickets1, lottoTickets2);
        Map<RankType, Integer> countRankType = tickets.matchCountAll(winningLotto);

        assertThat(countRankType.get(RankType.FIRST)).isEqualTo(1);
        assertThat(countRankType.get(RankType.SECOND)).isEqualTo(1);
        assertThat(countRankType.get(RankType.FOURTH)).isEqualTo(1);
        assertThat(countRankType.get(RankType.FIFTH)).isEqualTo(1);
    }
}