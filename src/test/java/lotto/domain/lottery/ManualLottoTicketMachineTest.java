package lotto.domain.lottery;

import static lotto.domain.lottery.LottoTicketUtil.generateLottoTicket;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import lotto.domain.purchase.LackOfMoneyException;
import lotto.domain.purchase.Money;

class ManualLottoTicketMachineTest {
    @DisplayName("수동 로또 기계 인스턴스 생성")
    @Test
    void constructor() {
        List<LottoTicket> lottoTickets = Arrays.asList(
                generateLottoTicket(1, 2, 3, 4, 5, 6),
                generateLottoTicket(2, 3, 4, 5, 6, 7),
                generateLottoTicket(3, 4, 5, 6, 7, 8)
        );

        assertThat(new ManualLottoTicketMachine(lottoTickets))
                .isInstanceOf(ManualLottoTicketMachine.class);
    }

    @DisplayName("로또 티켓이 null인 경우 예외 발생")
    @Test
    void constructor_LottoTicketsIsNull_ExceptionThrown() {
        assertThatThrownBy(() -> new ManualLottoTicketMachine(null))
                .isInstanceOf(NullPointerException.class)
                .hasMessageContaining("로또 티켓이 존재하지 않습니다");
    }

    @DisplayName("수동으로 로또 티켓 생성")
    @Test
    void createTicket() {
        ManualLottoTicketMachine manualLottoTicketMachine = new ManualLottoTicketMachine(
                Arrays.asList(
                        generateLottoTicket(1, 2, 3, 4, 5, 6),
                        generateLottoTicket(2, 3, 4, 5, 6, 7),
                        generateLottoTicket(3, 4, 5, 6, 7, 8)
                )
        );
        Money money = new Money(3_000);

        LottoTickets lottoTickets = manualLottoTicketMachine.createTickets(money);

        assertThat(lottoTickets.size()).isEqualTo(3);
    }

    @DisplayName("티켓 발급 중 금액이 부족할 때 예외 발생")
    @Test
    void createTicket_LackOfMoney_ExceptionThrown() {
        ManualLottoTicketMachine manualLottoTicketMachine = new ManualLottoTicketMachine(
                Arrays.asList(
                        generateLottoTicket(1, 2, 3, 4, 5, 6),
                        generateLottoTicket(2, 3, 4, 5, 6, 7),
                        generateLottoTicket(3, 4, 5, 6, 7, 8)
                )
        );
        Money money = new Money(2_000);

        assertThatThrownBy(() -> manualLottoTicketMachine.createTickets(money))
                .isInstanceOf(LackOfMoneyException.class)
                .hasMessageContaining("금액이 부족합니다");
    }
}
