package lotto.domain.lottery;

import static java.util.stream.Collectors.toList;

import java.util.Arrays;
import java.util.List;

class LottoTicketUtil {
    private LottoTicketUtil() {
        throw new AssertionError();
    }

    static LottoTicket generateLottoTicket(final int... numbers) {
        List<LottoNumber> lottoNumbers = generateLottoNumbers(numbers);
        return new LottoTicket(lottoNumbers);
    }

    static List<LottoNumber> generateLottoNumbers(final int... numbers) {
        return Arrays.stream(numbers)
                .mapToObj(LottoNumber::valueOf)
                .collect(toList());
    }

    static LottoTickets generateLottoTickets(final LottoTicket... tickets) {
        return new LottoTickets(Arrays.asList(tickets));
    }
}
