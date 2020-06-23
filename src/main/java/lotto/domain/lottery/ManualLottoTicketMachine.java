package lotto.domain.lottery;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import lotto.domain.purchase.Money;

public class ManualLottoTicketMachine implements LottoTicketMachine {
    private static final Money MANUAL_LOTTO_TICKET_PRICE = new Money(1_000);

    private final List<LottoTicket> lottoTickets;

    public ManualLottoTicketMachine(final List<LottoTicket> lottoTickets) {
        Objects.requireNonNull(lottoTickets, "로또 티켓이 존재하지 않습니다.");
        this.lottoTickets = lottoTickets;
    }

    @Override
    public LottoTickets createTickets(final Money money) {
        List<LottoTicket> createdTickets = new ArrayList<>();
        for (LottoTicket lottoTicket : lottoTickets) {
            money.spend(MANUAL_LOTTO_TICKET_PRICE);
            createdTickets.add(lottoTicket);
        }
        return new LottoTickets(createdTickets);
    }
}
