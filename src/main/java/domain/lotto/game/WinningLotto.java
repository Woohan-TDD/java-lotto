package domain.lotto.game;

import domain.lotto.result.LottoMatchResult;
import domain.lotto.ticket.Lotto;
import domain.lotto.ticket.LottoNumber;

public class WinningLotto {
	private final Lotto winning;
	private final LottoNumber bonus;

	public WinningLotto(Lotto winning, LottoNumber bonus) {
		this.winning = winning;
		this.bonus = bonus;
	}

	public LottoMatchResult calculateMatchResult(Lotto lotto) {
		return new LottoMatchResult(lotto.countMatchedNumber(winning), lotto.contains(bonus));
	}
}
