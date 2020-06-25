package lotto.dto;

import static java.util.stream.Collectors.collectingAndThen;
import static java.util.stream.Collectors.toList;

import java.util.List;

import lotto.domain.lottery.LottoNumber;
import lotto.domain.lottery.LottoTicket;
import lotto.domain.lottery.LottoTickets;

public class LottoTicketResponse {
    private List<Integer> numbers;

    public LottoTicketResponse(final List<Integer> numbers) {
        this.numbers = numbers;
    }

    public static List<LottoTicketResponse> fromLottoTickets(final LottoTickets lottoTickets) {
        return lottoTickets.getLottoTickets()
                .stream()
                .map(LottoTicketResponse::fromLottoTicket)
                .collect(toList());
    }

    private static LottoTicketResponse fromLottoTicket(final LottoTicket lottoTicket) {
        return lottoTicket.getLottoNumbers()
                .stream()
                .map(LottoNumber::getNumber)
                .collect(collectingAndThen(toList(), LottoTicketResponse::new));
    }

    public List<Integer> getNumbers() {
        return numbers;
    }
}
