package domain.lotto.rank;

import java.util.Arrays;

import domain.lotto.result.LottoMatchResult;

public enum LottoRank {
	FIRST(6, false, DefaultMatchingStrategy.DEFAULT, 2_000_000_000L),
	SECOND(5, true, DefaultMatchingStrategy.WITH_BONUS, 30_000_000L),
	THIRD(5, false, DefaultMatchingStrategy.WITHOUT_BONUS, 1_500_000L),
	FORTH(4, false, DefaultMatchingStrategy.DEFAULT, 50_000L),
	FIFTH(3, false, DefaultMatchingStrategy.DEFAULT, 5_000L),
	NOTHING(2, false, DefaultMatchingStrategy.UNDER, 0L);

	private final int matchCount;
	private final boolean needBonus;
	private final MatchingStrategy matchingStrategy;
	private final long winning;

	LottoRank(int matchCount, boolean needBonus, MatchingStrategy matchingStrategy, long winning) {
		this.matchCount = matchCount;
		this.matchingStrategy = matchingStrategy;
		this.needBonus = needBonus;
		this.winning = winning;
	}

	public static LottoRank findRank(LottoMatchResult lottoMatchResult) {
		return Arrays.stream(values())
			.filter(lottoRank -> lottoRank.isMatch(lottoMatchResult))
			.findAny()
			.orElseThrow(() -> new IllegalArgumentException("조건에 해당하는 Rank가 존재하지 않습니다."));
	}

	public long addWinning(long subTotal) {
		return winning + subTotal;
	}

	private boolean isMatch(LottoMatchResult lottoMatchResult) {
		return matchingStrategy.isMatch(lottoMatchResult, matchCount);
	}

	public int getMatchCount() {
		return matchCount;
	}

	public boolean needBonus() {
		return needBonus;
	}

	public long getWinning() {
		return winning;
	}

	@FunctionalInterface
	private interface MatchingStrategy {
		boolean isMatch(LottoMatchResult lottoMatchResult, int matchCount);
	}

	private enum DefaultMatchingStrategy implements MatchingStrategy {
		DEFAULT(LottoMatchResult::isEqualToMatchCount),
		WITH_BONUS((lottoMatchResult, matchCount) -> lottoMatchResult.isEqualToMatchCount(matchCount) && lottoMatchResult.hasBonus()),
		WITHOUT_BONUS((lottoMatchResult, matchCount) -> lottoMatchResult.isEqualToMatchCount(matchCount) && !lottoMatchResult.hasBonus()),
		UNDER(LottoMatchResult::isEqualToOrGraterThanMatchCount);

		private final MatchingStrategy matchingStrategy;

		DefaultMatchingStrategy(MatchingStrategy matchingStrategy) {
			this.matchingStrategy = matchingStrategy;
		}

		@Override
		public boolean isMatch(LottoMatchResult lottoMatchResult, int matchCount) {
			return matchingStrategy.isMatch(lottoMatchResult, matchCount);
		}
	}
}
