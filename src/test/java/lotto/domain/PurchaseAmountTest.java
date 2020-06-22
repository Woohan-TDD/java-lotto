package lotto.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PurchaseAmountTest {
    @DisplayName("수동 구입 개수를 입력하여 인스턴스 생성")
    @Test
    void constructor() {
        assertThat(new PurchaseAmount(10)).isInstanceOf(PurchaseAmount.class);
    }

    @DisplayName("입력한 구입 개수가 음수인 경우 예외 발생")
    @Test
    void constructor_PurchaseAmountSmallerThanZero_ExceptionThrown() {
        assertThatThrownBy(() -> new PurchaseAmount(-1))
                .isInstanceOf(InvalidPurchaseAmountException.class)
                .hasMessageContaining("구입할 금액이 올바르지 않습니다");
    }
}
