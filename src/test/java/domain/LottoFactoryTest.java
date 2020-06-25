package domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import utils.StringUtils;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static java.util.Arrays.asList;
import static org.assertj.core.api.Assertions.assertThat;

class LottoFactoryTest {

    private final LottoFactory lottoFactory = LottoFactory.getLottoFactory();

    @Test
    @DisplayName("6개의 로또 생성하기(자동생성)")
    void checkAutoLottoSize() {
        assertThat(lottoFactory.createAutoLotto().size()).isEqualTo(6);
    }

    @Test
    @DisplayName("자동생성된 6개의 로또가 서로 다른 번호인지 확인")
    void checkRandom() {
        Set<Integer> lotto = lottoFactory.createAutoLotto()
                .stream()
                .map(LottoNumber::getLottoNumber)
                .collect(Collectors.toSet());
        assertThat(lotto.size()).isEqualTo(6);
    }

    @Test
    @DisplayName("수동 생성 확인")
    void checkManualLotto() {
        List<LottoNumber> manualLotto = asList(
                new LottoNumber(1), new LottoNumber(2), new LottoNumber(3), new LottoNumber(4), new LottoNumber(5), new LottoNumber(6));
        assertThat(lottoFactory.createManualLotto(StringUtils.splitToInteger("1,2,3,4,5,6"))).isEqualTo(manualLotto);
    }

}