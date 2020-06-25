package domain.lotto.result;

import static java.util.stream.Collectors.*;

import java.util.List;

import domain.lotto.rank.LottoRank;
import domain.store.WinningMoney;

public class LottoResult {
	private final List<LottoRank> lottoRanks;

	public LottoResult(List<LottoRank> lottoRanks) {
		this.lottoRanks = lottoRanks;
	}

	public WinningMoney calculateWinning() {
		return new WinningMoney(lottoRanks.stream()
			.reduce(0L, ((total, lottoRank) -> lottoRank.addWinning(total)), Long::sum));
	}

	public RankResult countRank(LottoRank rank) {
		return lottoRanks.stream()
			.filter(lottoRank -> lottoRank.equals(rank))
			.collect(collectingAndThen(counting(), count -> new RankResult(rank, (int)(long)count)));
	}
}
