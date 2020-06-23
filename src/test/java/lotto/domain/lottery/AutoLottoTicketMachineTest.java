package lotto.domain.lottery;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import lotto.domain.purchase.Money;

class AutoLottoTicketMachineTest {
    @DisplayName("자동 로또 기계 인스턴스 생성")
    @Test
    void constructor() {
        Random random = ThreadLocalRandom.current();

        assertThat(new AutoLottoTicketMachine(random)).isInstanceOf(AutoLottoTicketMachine.class);
    }

    @DisplayName("난수 생성기가 null인 경우 예외 발생")
    @Test
    void constructor_RandomGeneratorIsNull_ExceptionThrown() {
        assertThatThrownBy(() -> new AutoLottoTicketMachine(null))
                .isInstanceOf(NullPointerException.class)
                .hasMessageContaining("난수 생성기가 존재하지 않습니다");
    }

    @DisplayName("입력받은 금액만큼 로또를 생성")
    @CsvSource(value = {"3000, 3", "0, 0"})
    @ParameterizedTest
    void createTickets(final int amount, final int expect) {
        Money money = new Money(amount);
        AutoLottoTicketMachine autoLottoTicketMachine = createAutoLottoTicketMachine();

        LottoTickets lottoTickets = autoLottoTicketMachine.createTickets(money);

        assertThat(lottoTickets.size()).isEqualTo(expect);
    }

    private AutoLottoTicketMachine createAutoLottoTicketMachine() {
        Random random = ThreadLocalRandom.current();
        return new AutoLottoTicketMachine(random);
    }
}
