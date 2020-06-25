package domain.lotto;

import static org.assertj.core.api.Assertions.*;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import helper.LottoProvider;

class LottoTicketsTest {
	@DisplayName("로또티켓과 Lotto들을 연결한다.")
	@Test
	void concat() {
		Lotto lottoA = LottoProvider.create(1, 2, 3, 4, 5, 6);
		Lotto lottoB = LottoProvider.create(7, 8, 9, 10, 11, 12);
		List<Lotto> head = Arrays.asList(lottoA);
		List<Lotto> tail = Arrays.asList(lottoB);

		LottoTickets lottoTickets = new LottoTickets(head);
		LottoTickets actual = lottoTickets.concat(tail);

		assertThat(actual.getTickets()).containsSequence(lottoA, lottoB);
	}
}