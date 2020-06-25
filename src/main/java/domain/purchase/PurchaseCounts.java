package domain.purchase;

import java.util.Objects;

public class PurchaseCounts {
    private final Count autoCount;
    private final Count manualCount;

    public PurchaseCounts(final Count autoCount, final Count manualCount) {
        if (Objects.isNull(autoCount) || Objects.isNull(manualCount)) {
            throw new IllegalArgumentException("잘못된 구매 개수 입력");
        }
        this.autoCount = autoCount;
        this.manualCount = manualCount;
    }

    public int getManualCount() {
        return manualCount.getCount();
    }

    public int getAutoCount() {
        return autoCount.getCount();
    }

}
