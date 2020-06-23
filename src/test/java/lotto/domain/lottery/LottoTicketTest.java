package lotto.domain.lottery;

import static lotto.domain.lottery.LottoTicketUtil.generateLottoNumbers;
import static lotto.domain.lottery.LottoTicketUtil.generateLottoTicket;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

class LottoTicketTest {
    @DisplayName("로또 번호를 입력하여 로또 한 장 생성")
    @Test
    void constructor() {
        List<LottoNumber> lottoNumbers = generateLottoNumbers(1, 2, 3, 4, 5, 6);
        assertThat(new LottoTicket(lottoNumbers)).isInstanceOf(LottoTicket.class);
    }

    @DisplayName("입력된 리스트가 null인 경우 예외 발생")
    @Test
    void constructor_ListIsNull_ExceptionThrown() {
        assertThatThrownBy(() -> new LottoTicket(null))
                .isInstanceOf(NullPointerException.class)
                .hasMessageContaining("입력받은 리스트가 존재하지 않습니다");
    }

    @DisplayName("입력받은 로또 개수가 올바르지 않은 경우 예외 발생")
    @Test
    void constructor_LottoNumbersSizeIsNotEqualToRequiredSize_ExceptionThrown() {
        List<LottoNumber> lottoNumbers = generateLottoNumbers(1, 2, 3, 4, 5, 6, 7);
        assertThatThrownBy(() -> new LottoTicket(lottoNumbers))
                .isInstanceOf(InvalidLottoNumberException.class)
                .hasMessageContaining("입력받은 로또 수가 올바르지 않습니다");
    }

    @DisplayName("중복되는 로또 번호가 존재하는 경우 예외 발생")
    @Test
    void constructor_DuplicatedLottoNumber_ExceptionThrown() {
        List<LottoNumber> lottoNumbers = generateLottoNumbers(1, 2, 3, 4, 5, 5);
        assertThatThrownBy(() -> new LottoTicket(lottoNumbers))
                .isInstanceOf(InvalidLottoNumberException.class)
                .hasMessageContaining("입력받은 로또 수에 중복이 존재합니다");
    }

    @DisplayName("숫자를 콤마 단위로 구분하여 입력받아 로또 티켓 생성")
    @ValueSource(strings = {"1, 2, 3, 4, 5, 6", "  1,   23, 34, 35   , 37, 42   "})
    @ParameterizedTest
    void ofComma(final String numbers) {
        assertThat(LottoTicket.ofComma(numbers)).isInstanceOf(LottoTicket.class);
    }

    @DisplayName("특정한 로또 번호를 포함하고 있는지 확인")
    @CsvSource(value = {"2,true", "7,false"})
    @ParameterizedTest
    void contains(final int number, final boolean expect) {
        LottoTicket lottoTicket = generateLottoTicket(1, 2, 3, 4, 5, 6);
        LottoNumber lottoNumber = LottoNumber.valueOf(number);

        assertThat(lottoTicket.contains(lottoNumber)).isEqualTo(expect);
    }

    @DisplayName("생성된 로또 티켓의 값이 같은지 확인")
    @Test
    void getLottoNumbers() {
        LottoTicket lottoTicket = generateLottoTicket(1, 2, 3, 4, 5, 6);
        assertThat(lottoTicket.getLottoNumbers()).isEqualTo(generateLottoNumbers(1, 2, 3, 4, 5, 6));
    }

    @DisplayName("같은 로또 수를 가지는 티켓이 논리적으로 동일한지 확인")
    @Test
    void equals() {
        LottoTicket lottoTicket1 = generateLottoTicket(1, 2, 3, 4, 5, 6);
        LottoTicket lottoTicket2 = generateLottoTicket(4, 5, 6, 1, 2, 3);

        assertThat(lottoTicket1).isEqualTo(lottoTicket2);
    }
}
