package lotto.domain.lottery;

import static java.util.stream.Collectors.collectingAndThen;
import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.groupingBy;

import java.util.List;
import java.util.Objects;

public class WinningLotto {
    private final LottoTicket lottoTicket;
    private final LottoNumber bonusNumber;

    public WinningLotto(final LottoTicket lottoTicket, final LottoNumber bonusNumber) {
        validateWinningLotto(lottoTicket, bonusNumber);
        this.lottoTicket = lottoTicket;
        this.bonusNumber = bonusNumber;
    }

    public MatchResult matchAll(final List<LottoTicket> tickets) {
        return tickets.stream()
                .map(this::match)
                .collect(collectingAndThen(groupingBy(rank -> rank, counting()), MatchResult::new));
    }

    private Rank match(final LottoTicket ticket) {
        return Rank.match(lottoTicket.match(ticket), containsBonus(ticket));
    }

    public boolean containsBonus(final LottoTicket ticket) {
        return ticket.contains(bonusNumber);
    }

    private void validateWinningLotto(final LottoTicket lottoTicket, final LottoNumber bonusNumber) {
        Objects.requireNonNull(lottoTicket, "로또 티켓이 존재하지 않습니다.");
        Objects.requireNonNull(bonusNumber, "보너스 번호가 존재하지 않습니다.");
        validateDuplicate(lottoTicket, bonusNumber);
    }

    private void validateDuplicate(final LottoTicket lottoTicket, final LottoNumber bonusNumber) {
        if (lottoTicket.contains(bonusNumber)) {
            throw new InvalidWinningLottoException("우승 번호와 보너스 번호가 중복됩니다.");
        }
    }
}
