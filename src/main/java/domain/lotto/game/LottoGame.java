package domain.lotto.game;

import static java.util.stream.Collectors.*;

import java.util.Collections;
import java.util.List;

import domain.lotto.result.LottoMatchResults;
import domain.lotto.ticket.Lotto;

public class LottoGame {
	private final List<Lotto> lottos;

	public LottoGame(List<Lotto> lottos) {
		this.lottos = lottos;
	}

	public LottoMatchResults calculateResults(WinningLotto winningLotto) {
		return lottos.stream()
			.map(winningLotto::calculateMatchResult)
			.collect(collectingAndThen(toList(), LottoMatchResults::new));
	}

	public List<Lotto> getLottos() {
		return Collections.unmodifiableList(lottos);
	}
}
