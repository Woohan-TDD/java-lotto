package lotto.domain.lottery;

import java.util.Objects;

public class WinningLotto {
    private final LottoTicket lottoTicket;
    private final LottoNumber bonusNumber;

    public WinningLotto(final LottoTicket lottoTicket, final LottoNumber bonusNumber) {
        validateWinningLotto(lottoTicket, bonusNumber);
        this.lottoTicket = lottoTicket;
        this.bonusNumber = bonusNumber;
    }

    public int match(final LottoTicket ticket) {
        return lottoTicket.match(ticket);
    }

    public boolean containsBonus(final LottoTicket ticket) {
        return ticket.contains(bonusNumber);
    }

    public boolean excludeBonus(final LottoTicket ticket) {
        return !ticket.contains(bonusNumber);
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
