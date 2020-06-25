package domain.purchase;

public class Count {

    private static final int MIN_COUNT = 0;
    private final int count;

    private Count(final int count) {
        validateManualCount(count);
        this.count = count;
    }

    public static Count createAutoCount(Money money, Count manualCount) {
        return new Count(manualCount.calculateAutoCount(money));
    }

    public static Count createManualCount(Money money, int manualCount) {
        validateManualCount(money, manualCount);
        return new Count(manualCount);
    }

    private static void validateManualCount(Money money, int manualCount) {
        if (money.calculateCount() < manualCount) {
            throw new IllegalArgumentException("해당 금액으로 살 수 있는 수동 로또 개수를 넘어섰습니다.");
        }
    }

    private void validateManualCount(int count) {
        if (count < MIN_COUNT) {
            throw new IllegalArgumentException("개수는 0 이상이어야 합니다.");
        }
    }

    public int calculateAutoCount(Money money) {
        return money.calculateCount() - this.count;
    }

    public int getCount() {
        return count;
    }
}
