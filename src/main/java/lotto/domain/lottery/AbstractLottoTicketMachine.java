package lotto.domain.lottery;

import lotto.domain.purchase.Money;

public abstract class AbstractLottoTicketMachine implements LottoTicketMachine {
    private static final Money LOTTO_TICKET_PRICE = new Money(1_000);

    @Override
    public LottoTickets createTickets(final Money money) {
        Money ticketPrice = getPrice();
        return createTickets(money, ticketPrice);
    }

    protected abstract LottoTickets createTickets(final Money money, final Money price);

    protected Money getPrice() {
        return LOTTO_TICKET_PRICE;
    }
}
