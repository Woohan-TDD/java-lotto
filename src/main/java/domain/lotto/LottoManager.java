package domain.lotto;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import domain.amount.LottoMoney;
import domain.statistics.Rank;

public class LottoManager {
	private final LottoTickets lottoTickets;
	private final WinningLotto winningLotto;

	public LottoManager(LottoTickets lottoTickets, WinningLotto winningLotto) {
		Objects.requireNonNull(lottoTickets);
		Objects.requireNonNull(winningLotto);

		this.lottoTickets = lottoTickets;
		this.winningLotto = winningLotto;
	}

	public Map<Rank, Integer> calculateRanks() {
		Map<Rank, Integer> ranks = new HashMap<>();

		for (Lotto lotto : lottoTickets.getTickets()) {
			int match = winningLotto.countMatch(lotto);
			boolean isBonusMatch = winningLotto.checkBonus(lotto);

			Rank rank = Rank.of(match, isBonusMatch);
			Integer rankByCount = ranks.getOrDefault(rank, 0);

			ranks.put(rank, rankByCount + 1);
		}

		return ranks;
	}

	public double calculateProfit(Map<Rank, Integer> countByRank, LottoMoney lottoMoney) {
		Objects.requireNonNull(countByRank);
		Objects.requireNonNull(lottoMoney);

		long winningMoney = 0L;

		for (Rank rank : Rank.values()) {
			int count = countByRank.getOrDefault(rank, 0);
			winningMoney += rank.multiplyMoneyByCount(count);
		}

		return lottoMoney.calculateProfit(winningMoney);
	}
}
