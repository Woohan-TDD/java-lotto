package domain.purchase;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class CountTest {

    private final Money money = new Money(3000);

    @Test
    @DisplayName("정상 범위의 수동 구매 개수 반환")
    void createManualCount() {
        Count count = Count.createManualCount(money, 2);
        assertThat(count.getCount()).isEqualTo(2);
    }

    @Test
    @DisplayName("구매 가능한 범위 밖의 수동 개수시 예외처리")
    void createManualCountException() {
        assertThatThrownBy(() -> Count.createManualCount(money, 4))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("해당 금액으로 살 수 있는 수동 로또 개수를 넘어섰습니다.");
    }

    @Test
    @DisplayName("정상 범위의 자동 구매 금액에 따른 개수 테스트")
    void createAutoCount() {
        Count count = Count.createAutoCount(money, Count.createManualCount(money, 2));
        assertThat(count.getCount()).isEqualTo(1);
    }

    @Test
    @DisplayName("수동 개수에 따른 자동 개수 계산 테스트")
    void calculateAutoCount() {
        Count count = Count.createManualCount(money, 1);
        assertThat(count.calculateAutoCount(money)).isEqualTo(2);
    }

    @Test
    @DisplayName("음수 개수(정상범위 밖) 들어왔을 때 예외처리")
    void validateCount() {
        assertThatThrownBy(() -> Count.createAutoCount(
                new Money(2000), Count.createManualCount(money, 3)))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("개수는 0 이상이어야 합니다.");
    }

}