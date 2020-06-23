package domain.lotto.result;

import static java.util.stream.Collectors.*;

import java.util.List;
import java.util.Objects;

import domain.store.WinningMoney;
import domain.lotto.rank.LottoRank;

public class LottoResult {
	private List<LottoRank> lottoRanks;

	public LottoResult(List<LottoRank> lottoRanks) {
		this.lottoRanks = lottoRanks;
	}

	public WinningMoney calculateWinning() {
		return lottoRanks.stream()
			.reduce(new WinningMoney(0), ((total, lottoRank) -> lottoRank.addWinning(total)), WinningMoney::sum);
	}

	public RankResult countRank(LottoRank rank) {
		return lottoRanks.stream()
			.filter(lottoRank -> Objects.equals(rank, lottoRank))
			.collect(collectingAndThen(counting(), count -> new RankResult(rank, (int)(long) count)));
	}
}