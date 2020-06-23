package lotto.domain.lottery;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class LottoTickets {
    private final List<LottoTicket> lottoTickets;

    public LottoTickets(final List<LottoTicket> lottoTickets) {
        Objects.requireNonNull(lottoTickets, "로또 리스트가 null입니다.");
        this.lottoTickets = lottoTickets;
    }

    public int size() {
        return lottoTickets.size();
    }

    public LottoTickets concat(final LottoTickets that) {
        ArrayList<LottoTicket> concatenatedLottoTickets = new ArrayList<>(this.lottoTickets);
        concatenatedLottoTickets.addAll(that.lottoTickets);
        return new LottoTickets(concatenatedLottoTickets);
    }

    public List<LottoTicket> getLottoTickets() {
        return Collections.unmodifiableList(lottoTickets);
    }
}
