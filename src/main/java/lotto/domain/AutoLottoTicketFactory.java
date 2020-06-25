package lotto.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class AutoLottoTicketFactory implements LottoTicketFactory {

    private static final int MINIMUM_NUMBER = 1;
    private static final int MAXIMUM_NUMBER = 45;
    private static final int LOTTO_SIZE = 6;

    private final LottoCount lottoCount;

    private AutoLottoTicketFactory(LottoCount lottoCount) {
        Objects.requireNonNull(lottoCount, "Null 감지");
        this.lottoCount = lottoCount;
    }

    public static AutoLottoTicketFactory from(LottoCount lottoCount) {
        return new AutoLottoTicketFactory(lottoCount);
    }

    @Override
    public List<LottoTicket> create() {
        List<LottoTicket> lottoTickets = new ArrayList<>();
        for (int count = 0; count < this.lottoCount.getCount(); count++) {
            lottoTickets.add(LottoTicket.from(createTicket()));
        }
        return lottoTickets;
    }

    private List<LottoNumber> createTicket() {
        List<Integer> numbers = IntStream
                .range(MINIMUM_NUMBER, MAXIMUM_NUMBER + 1)
                .boxed()
                .collect(Collectors.toList());
        Collections.shuffle(numbers);
        return numbers.stream()
                .limit(LOTTO_SIZE)
                .sorted()
                .map(LottoNumber::from)
                .collect(Collectors.toList());
    }
}