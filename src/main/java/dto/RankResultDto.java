package dto;

import domain.lotto.rank.LottoRank;
import domain.lotto.result.RankResult;

public class RankResultDto {
	private int matchCount;
	private boolean needBonus;
	private long winning;
	private int totalCount;

	public RankResultDto(int matchCount, boolean needBonus, long winning, int totalCount) {
		this.matchCount = matchCount;
		this.needBonus = needBonus;
		this.winning = winning;
		this.totalCount = totalCount;
	}

	public static RankResultDto of(RankResult rankResult) {
		LottoRank lottoRank = rankResult.getLottoRank();
		return new RankResultDto(lottoRank.getMatchCount(), lottoRank.needBonus(), lottoRank.getWinning(),
			rankResult.getCount());
	}

	public int getMatchCount() {
		return matchCount;
	}

	public boolean isNeedBonus() {
		return needBonus;
	}

	public long getWinning() {
		return winning;
	}

	public int getTotalCount() {
		return totalCount;
	}
}
