package domain.purchase;

import domain.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import utils.StringUtils;

import java.util.List;

import static java.util.Arrays.asList;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class MoneyTest {

    @ParameterizedTest
    @ValueSource(ints = {1000, 3000})
    @DisplayName("정상범위의 구입금액 테스트")
    void money (int value) {
        Money money = new Money(value);
        assertThat(money).isNotNull();
    }

    @ParameterizedTest
    @ValueSource(ints = {-3000, 999})
    @DisplayName("정상범위 외의 구입금액 예외테스트")
    void validateMoney(int value) {
        assertThatThrownBy(() -> new Money(value))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("1000원 이상의 금액을 입력 해 주세요.");
    }

    @ParameterizedTest
    @ValueSource(ints = {3333, 2001})
    @DisplayName("1000원단위 입력에 대한 예외처리")
    void validate(int value) {
        assertThatThrownBy(() -> new Money(value))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("1000원 단위로 입력하세요.");
    }

    @Test
    @DisplayName("구매 금액에 따른 몫(구매 가능 로또 갯수) 반환 테스트")
    void calculate() {
        Money money = new Money(3000);
        assertThat(money.calculateCount()).isEqualTo(3);
    }

    @Test
    @DisplayName("수익률계산")
    void calculateRate() {
        Money money = new Money(5000);
        List<Lotto> lottoList = asList(Lotto.createManualLotto(StringUtils.splitToInteger("1,2,3,4,5,6")),
                Lotto.createManualLotto(StringUtils.splitToInteger("1,2,3,4,5,7")),
                Lotto.createManualLotto(StringUtils.splitToInteger("1,2,3,4,5,8")),
                Lotto.createManualLotto(StringUtils.splitToInteger("1,2,3,10,15,16")),
                Lotto.createManualLotto(StringUtils.splitToInteger("11,12,13,14,15,16")));
        Lottos lottos = new Lottos(lottoList);
        WinningLotto winningLotto = new WinningLotto(
                Lotto.createManualLotto(StringUtils.splitToInteger("1,2,3,4,5,6")), new LottoNumber(8));
        WinningResult winningResult = new WinningResult(lottos, winningLotto);

        assertThat(money.calculateWinningRate(winningResult)).isEqualTo(406300);
    }

}