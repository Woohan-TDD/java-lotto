package domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import utils.StringUtils;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class LottoTest {

    @Test
    @DisplayName("자동 로또 생성 확인")
    void checkAutoLotto() {
        assertThat(Lotto.createAutoLotto().getLotto().size()).isEqualTo(6);
    }

    @Test
    @DisplayName("수동 로또 생성 확인")
    void checkManualLotto() {
        List<Integer> lottoNumbers = StringUtils.splitToInteger("1,2,3,4,5,6");
        assertThat(Lotto.createManualLotto(lottoNumbers).getLotto().size()).isEqualTo(6);
    }

    @ParameterizedTest
    @ValueSource(strings = {"1,2,3,4", "1,2,3,4,5,6,7"})
    @DisplayName("6개가 아닌 수동 로또 예외처리")
    void checkManualLottoSize(String input) {
        List<Integer> lottoNumbers = StringUtils.splitToInteger(input);
        assertThatThrownBy(() -> Lotto.createManualLotto(lottoNumbers))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("로또 한장은 6개의 숫자로 이루어져 있어야 합니다.");
    }

    @Test
    @DisplayName("로또 중복에 대한 예외처리")
    void checkDuplication() {
        List<Integer> lottoNumbers = StringUtils.splitToInteger("1,1,2,3,4,5");
        assertThatThrownBy(() -> Lotto.createManualLotto(lottoNumbers))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("로또 한장은 서로 다른 숫자로 이루어져 있어야 합니다.");
    }

    @Test
    @DisplayName("해당 번호가 있는지 확인")
    void contains() {
        List<Integer> lottoNumbers = StringUtils.splitToInteger("1,6,2,3,4,5");
        Lotto lotto = Lotto.createManualLotto(lottoNumbers);
        assertThat(lotto.contains(new LottoNumber(1))).isTrue();
        assertThat(lotto.contains(new LottoNumber(10))).isFalse();
    }

}