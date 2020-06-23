package lotto.domain.lottery;

import static lotto.domain.lottery.LottoTicketUtil.generateLottoTicket;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class LottoTicketMachineFactoryTest {
    @DisplayName("자동 로또 기계 생성")
    @Test
    void createAutoLottoTicketMachine() {
        assertThat(LottoTicketMachineFactory.createAutoTicketMachine())
                .isInstanceOf(LottoTicketMachine.class);
    }

    @DisplayName("수동 로또 기계 생성")
    @Test
    void createManualLottoTicketMachine() {
        List<LottoTicket> lottoTickets = Arrays.asList(
                generateLottoTicket(1, 2, 3, 4, 5, 6),
                generateLottoTicket(1, 2, 3, 4, 5, 6),
                generateLottoTicket(1, 2, 3, 4, 5, 6)
        );

        assertThat(LottoTicketMachineFactory.createManualTicketMachine(lottoTickets))
                .isInstanceOf(LottoTicketMachine.class);
    }
}
