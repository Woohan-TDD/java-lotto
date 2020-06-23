package lotto.dto;

import static java.util.stream.Collectors.toList;

import java.util.List;
import java.util.stream.Stream;

import lotto.domain.lottery.MatchResult;
import lotto.domain.lottery.Rank;

public class LottoResultResponse {
    private List<LottoRankResponse> lottoRankResponses;
    private Long profitRate;

    public LottoResultResponse(final List<LottoRankResponse> lottoRankResponses, final Long profitRate) {
        this.lottoRankResponses = lottoRankResponses;
        this.profitRate = profitRate;
    }

    public static LottoResultResponse fromMatchResultAndTotalSpendMoney(final MatchResult matchResult,
            final int totalSpendMoney) {
        return new LottoResultResponse(generateLottoRankResponses(matchResult),
                matchResult.calculateProfitRate(totalSpendMoney));
    }

    private static List<LottoRankResponse> generateLottoRankResponses(final MatchResult matchResult) {
        return Stream.of(Rank.values())
                .map(rank -> LottoRankResponse.fromRank(rank, matchResult.getCount(rank)))
                .collect(toList());
    }

    public List<LottoRankResponse> getLottoRankResponses() {
        return lottoRankResponses;
    }

    public void setLottoRankResponses(final List<LottoRankResponse> lottoRankResponses) {
        this.lottoRankResponses = lottoRankResponses;
    }

    public long getProfitRate() {
        return profitRate;
    }

    public void setProfitRate(final long profitRate) {
        this.profitRate = profitRate;
    }
}
