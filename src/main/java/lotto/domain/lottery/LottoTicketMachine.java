package lotto.domain.lottery;

import lotto.domain.purchase.Money;

public interface LottoTicketMachine {
    LottoTickets createTickets(final Money money);
}
