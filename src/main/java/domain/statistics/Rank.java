package domain.statistics;

import java.util.Arrays;

public enum Rank {
	SIX(6, 2_000_000_000L, LottoMatchStrategy.DEFAULT),
	FIVE_BONUS(5, 30_000_000L, LottoMatchStrategy.BONUS),
	FIVE(5, 1_500_000L, LottoMatchStrategy.NO_BONUS),
	FOUR(4, 50_000L, LottoMatchStrategy.DEFAULT),
	THREE(3, 5_000L, LottoMatchStrategy.DEFAULT),
	NONE_MATCH(0, 0L, LottoMatchStrategy.UNDER);

	private final int match;
	private final long money;
	private final LottoMatchStrategy lottoMatchStrategy;

	Rank(int match, long money, LottoMatchStrategy lottoMatchStrategy) {
		this.lottoMatchStrategy = lottoMatchStrategy;
		this.match = match;
		this.money = money;
	}

	public static Rank of(int match, boolean hasBonusMatch) {
		return Arrays.stream(values())
			.filter(rank -> rank.isMatch(match, hasBonusMatch))
			.findAny()
			.orElse(NONE_MATCH);
	}

	private boolean isMatch(int match, boolean hasBonusMatch) {
		return lottoMatchStrategy.isMatch(this, match, hasBonusMatch);
	}

	private boolean isSameMatch(int match) {
		return this.match == match;
	}

	public long multiplyMoneyByCount(int count) {
		return this.money * count;
	}

	public int getMatch() {
		return match;
	}

	public long getMoney() {
		return money;
	}

	private enum LottoMatchStrategy implements MatchStrategy {
		DEFAULT((rank, match, bonusMatch) -> rank.isSameMatch(match)),
		BONUS((rank, match, bonusMatch) -> rank.isSameMatch(match) && bonusMatch),
		NO_BONUS((rank, match, bonusMatch) -> rank.isSameMatch(match) && !bonusMatch),
		UNDER((rank, match, bonusMatch) -> match < 3);

		private final MatchStrategy matchStrategy;

		LottoMatchStrategy(MatchStrategy matchStrategy) {
			this.matchStrategy = matchStrategy;
		}

		@Override
		public boolean isMatch(Rank rank, int match, boolean bonusMatch) {
			return matchStrategy.isMatch(rank, match, bonusMatch);
		}
	}

	@FunctionalInterface
	private interface MatchStrategy {
		boolean isMatch(Rank rank, int match, boolean bonusMatch);
	}
}
