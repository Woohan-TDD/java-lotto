package lotto.domain;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class ManualLottoTicketFactoryTest {

    @Test
    void create() {
        LottoTicket lottoTicket1 = LottoTicketUtil.getLottoTicket(1);
        LottoTicket lottoTicket2 = LottoTicketUtil.getLottoTicket(2);

        List<LottoTicket> expected = Arrays.asList(lottoTicket1, lottoTicket2);

        List<String> tickets = Arrays.asList(
            "1, 2, 3, 4, 5, 6",
            "2, 3, 4, 5, 6, 7"
        );

        LottoTicketFactory manualTicketFactory = ManualLottoTicketFactory.from(tickets);
        List<LottoTicket> actual = manualTicketFactory.create();

        assertThat(actual).isEqualTo(expected);
    }
}