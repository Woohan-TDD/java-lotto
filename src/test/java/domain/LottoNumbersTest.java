package domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class LottoNumbersTest {

    private static final LottoNumbers lottoNumbers = LottoNumbers.newLottoNumbers();

    @Test
    @DisplayName("45개의 로또번호가 잘 생성되었는지 확인")
    void lottoNumbersSizeCheck() {
        assertThat(lottoNumbers.getLottoNumbers().size()).isEqualTo(45);
    }

    @Test
    void valueOfTest() {
        assertThat(lottoNumbers.valueOf(3)).isEqualTo(new LottoNumber(3));
        assertThat(lottoNumbers.valueOf(3)).isNotEqualTo(new LottoNumber(4));
    }

}