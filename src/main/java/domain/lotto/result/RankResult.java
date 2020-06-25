package domain.lotto.result;

import domain.lotto.rank.LottoRank;

public class RankResult {
	private final LottoRank lottoRank;
	private final int count;

	public RankResult(LottoRank lottoRank, int count) {
		validate(count);
		this.lottoRank = lottoRank;
		this.count = count;
	}

	private void validate(int count) {
		if (count < 0) {
			throw new IllegalArgumentException("count는 음수일 수 없습니다. input : " + count);
		}
	}

	public LottoRank getLottoRank() {
		return lottoRank;
	}

	public int getCount() {
		return count;
	}
}
