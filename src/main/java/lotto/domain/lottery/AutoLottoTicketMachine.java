package lotto.domain.lottery;

import static java.util.stream.Collectors.collectingAndThen;
import static java.util.stream.Collectors.toList;
import static lotto.domain.lottery.LottoNumber.MAX_NUMBER;
import static lotto.domain.lottery.LottoNumber.MIN_NUMBER;
import static lotto.domain.lottery.LottoTicket.TOTAL_LOTTO_NUMBER;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Random;

import lotto.domain.purchase.Money;

public class AutoLottoTicketMachine extends AbstractLottoTicketMachine {
    private final Random random;

    public AutoLottoTicketMachine(final Random random) {
        Objects.requireNonNull(random, "난수 생성기가 존재하지 않습니다.");
        this.random = random;
    }

    @Override
    protected LottoTickets createTickets(final Money money, final Money price) {
        List<LottoTicket> lottoTickets = new ArrayList<>();
        while (money.hasAvailableAmount()) {
            money.spend(price);
            LottoTicket ticket = createTicket();
            lottoTickets.add(ticket);
        }
        return new LottoTickets(lottoTickets);
    }

    private LottoTicket createTicket() {
        return random.ints(MIN_NUMBER, MAX_NUMBER + 1)
                .distinct()
                .limit(TOTAL_LOTTO_NUMBER)
                .mapToObj(LottoNumber::valueOf)
                .collect(collectingAndThen(toList(), LottoTicket::new));
    }
}
