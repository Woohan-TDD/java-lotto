package domain.purchase;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullSource;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

class PurchaseCountsTest {

    @ParameterizedTest
    @NullSource
    @DisplayName("null 체크 테스트")
    void nullTest(Object object) {
        assertThatThrownBy(() -> new PurchaseCounts((Count) object, (Count) object))
                .isInstanceOf(IllegalArgumentException.class);
    }

}