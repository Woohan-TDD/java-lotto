package lotto.domain.lottery;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import lotto.domain.purchase.Money;

public class ManualLottoTicketMachine extends AbstractLottoTicketMachine {
    private final List<LottoTicket> lottoTickets;

    public ManualLottoTicketMachine(final List<LottoTicket> lottoTickets) {
        Objects.requireNonNull(lottoTickets, "로또 티켓이 존재하지 않습니다.");
        this.lottoTickets = lottoTickets;
    }

    @Override
    protected LottoTickets createTickets(final Money money, final Money price) {
        List<LottoTicket> createdTickets = new ArrayList<>();
        for (LottoTicket lottoTicket : lottoTickets) {
            money.spend(price);
            createdTickets.add(lottoTicket);
        }
        return new LottoTickets(createdTickets);
    }
}
