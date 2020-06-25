package domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import utils.StringUtils;

import java.util.List;
import java.util.Map;

import static java.util.Arrays.asList;
import static org.assertj.core.api.Assertions.assertThat;

class WinningResultTest {

    private Lottos lottos;
    private WinningLotto winningLotto;

    @BeforeEach
    void setUp() {
        List<Lotto> lottoList = asList(Lotto.createManualLotto(StringUtils.splitToInteger("1,2,3,4,5,6")),
                Lotto.createManualLotto(StringUtils.splitToInteger("1,2,3,4,5,7")),
                Lotto.createManualLotto(StringUtils.splitToInteger("1,2,3,4,5,8")),
                Lotto.createManualLotto(StringUtils.splitToInteger("1,2,3,10,15,16")),
                Lotto.createManualLotto(StringUtils.splitToInteger("11,12,13,14,15,16")));
        lottos = new Lottos(lottoList);
        winningLotto = new WinningLotto(
                Lotto.createManualLotto(StringUtils.splitToInteger("1,2,3,4,5,6")), new LottoNumber(8));
    }

    @ParameterizedTest
    @EnumSource(Rank.class)
    @DisplayName("초기화 확인")
    void init(Rank rank) {
        WinningResult winningResult = new WinningResult();
        Map<Rank, Integer> result = winningResult.getWinningResult();
        assertThat(result.size()).isEqualTo(6);
        assertThat(result.get(rank).intValue()).isEqualTo(0);
    }

    @Test
    @DisplayName("당첨 개수 확인")
    void calculateResult() {
        WinningResult winningResult = new WinningResult(lottos, winningLotto);
        Map<Rank, Integer> result = winningResult.getWinningResult();

        assertThat(result.get(Rank.FIRST).intValue()).isEqualTo(1);
        assertThat(result.get(Rank.SECOND).intValue()).isEqualTo(1);
        assertThat(result.get(Rank.THIRD).intValue()).isEqualTo(1);
        assertThat(result.get(Rank.FOURTH).intValue()).isEqualTo(0);
        assertThat(result.get(Rank.FIFTH).intValue()).isEqualTo(1);
        assertThat(result.get(Rank.NONE).intValue()).isEqualTo(1);
    }

    @Test
    @DisplayName("총 얻은 수익금 반환")
    void calculateResultMoney() {
        WinningResult winningResult = new WinningResult(lottos, winningLotto);
        assertThat(winningResult.calculateWinningMoney()).isEqualTo(2031505000);
    }
}