package domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import utils.StringUtils;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class WinningLottoTest {

    private final LottoNumbers lottoNumbers = LottoNumbers.newLottoNumbers();

    @Test
    @DisplayName("당첨로또 생성")
    void createWinningLotto() {
        Lotto lotto = Lotto.createManualLotto(StringUtils.splitToInteger("1,2,3,4,5,6"));
        WinningLotto winningLotto = new WinningLotto(lotto, lottoNumbers.valueOf(7));
        assertThat(winningLotto).isNotNull();
    }

    @Test
    @DisplayName("보너스볼 중복에 대한 예외처리")
    void validateBonus() {
        Lotto lotto = Lotto.createManualLotto(StringUtils.splitToInteger("1,2,3,4,5,6"));
        assertThatThrownBy(() -> new WinningLotto(lotto, lottoNumbers.valueOf(1)))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("보너스볼은 로또 번호와 중복되면 안 됩니다.");
    }

    @Test
    @DisplayName("몇 개의 번호가 일치하는지 확인")
    void calculateMatch() {
        List<Integer> lottoNumbers = StringUtils.splitToInteger("1,6,2,3,4,5");
        List<Integer> WinningLottoNumbers = StringUtils.splitToInteger("1,6,2,3,41,10");
        Lotto lotto = Lotto.createManualLotto(lottoNumbers);
        WinningLotto winningLotto = new WinningLotto(Lotto.createManualLotto(WinningLottoNumbers), new LottoNumber(14));

        assertThat(winningLotto.calculateMatch(lotto)).isEqualTo(4);
    }

    @Test
    @DisplayName("보너스번호가 일치하는지 확인")
    void isBonus() {
        List<Integer> lottoNumbers = StringUtils.splitToInteger("1,6,2,3,4,5");
        List<Integer> WinningLottoNumbers = StringUtils.splitToInteger("1,6,2,3,41,10");
        Lotto lotto = Lotto.createManualLotto(lottoNumbers);
        WinningLotto winningLotto = new WinningLotto(Lotto.createManualLotto(WinningLottoNumbers), new LottoNumber(5));

        assertThat(winningLotto.isBonus(lotto)).isTrue();
    }

}