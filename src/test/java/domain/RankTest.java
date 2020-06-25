package domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import utils.StringUtils;

import static org.assertj.core.api.Assertions.assertThat;

class RankTest {

    @ParameterizedTest
    @CsvSource(value = {"1,2,3,4,5,6:FIRST",
            "1,2,3,4,5,8:SECOND",
            "1,2,3,4,5,7:THIRD",
            "1,2,3,10,15,16:FIFTH",
            "11,12,13,14,15,16:NONE",},
            delimiter = ':')
    @DisplayName("로또 한장에 따른 등수 구하기")
    void calculate(String input, Rank rank) {
        Lotto lotto = Lotto.createManualLotto(StringUtils.splitToInteger(input));
        WinningLotto winningLotto = new WinningLotto(
                Lotto.createManualLotto(StringUtils.splitToInteger("1,2,3,4,5,6")), new LottoNumber(8));

        assertThat(Rank.findRank(lotto, winningLotto)).isEqualTo(rank);

    }

}