package domain.lotto;

import static java.util.stream.Collectors.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

public class LottoTickets {
	private final List<Lotto> tickets;

	public LottoTickets(List<Lotto> lottos) {
		this.tickets = new ArrayList<>(lottos);
	}

	public LottoTickets concat(List<Lotto> lottos) {
		return Stream.concat(tickets.stream(), lottos.stream())
			.collect(collectingAndThen(toList(), LottoTickets::new));
	}

	public List<Lotto> getTickets() {
		return Collections.unmodifiableList(tickets);
	}
}
