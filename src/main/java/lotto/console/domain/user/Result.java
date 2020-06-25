package lotto.console.domain.user;

import java.util.List;
import java.util.Objects;

public class Result {

	private final static int PERCENT = 100;

	private final Money money;
	private final List<Rank> ranks;

	public Result(final Money money, final List<Rank> ranks) {
		Objects.requireNonNull(money);
		this.money = money;
		this.ranks = ranks;
	}

	public double calculateEarningRate() {
		final double profit = totalWinningMoney() - money.getMoney();

		return (profit / money.getMoney()) * PERCENT;
	}

	public double totalWinningMoney() {
		return ranks.stream()
			.mapToInt(Rank::getWinningMoney)
			.sum();
	}

	public long sizeBy(final Rank rank) {
		return ranks.stream()
			.filter(rank::equals)
			.count();
	}
}