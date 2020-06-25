package lotto.console.domain.user;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import lotto.console.domain.ball.WinningBalls;
import lotto.console.domain.ball.LottoTicket;

public class User {

	private final Money money;
	private final CreationCount creationCount;
	private final List<LottoTicket> lottoTickets;

	public User(final Money money, final CreationCount creationCount,
		final List<LottoTicket> lottoTickets) {
		Objects.requireNonNull(money);
		Objects.requireNonNull(creationCount);
		this.money = money;
		this.creationCount = creationCount;
		this.lottoTickets = lottoTickets;
	}

	public List<Rank> calculateRanks(final WinningBalls winningBalls) {
		return Collections.unmodifiableList(lottoTickets.stream()
			.map(lottoTicket -> Rank.of(lottoTicket, winningBalls))
			.collect(Collectors.toList()));
	}

	public Money getMoney() {
		return money;
	}

	public CreationCount getCreationCount() {
		return creationCount;
	}

	public List<LottoTicket> getLottoTickets() {
		return Collections.unmodifiableList(lottoTickets);
	}
}