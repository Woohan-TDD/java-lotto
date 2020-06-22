package lotto.domain;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class LottoNumber {
    private static final int MIN_NUMBER = 1;
    private static final int MAX_NUMBER = 45;

    private final int number;

    private LottoNumber(final int number) {
        validateNumberRange(number);
        this.number = number;
    }

    public static LottoNumber valueOf(final int number) {
        validateNumberRange(number);
        return LottoNumberCache.get(number);
    }

    private static void validateNumberRange(final int number) {
        if (number < MIN_NUMBER || number > MAX_NUMBER) {
            throw new InvalidLottoNumberException("로또 수의 범위가 올바르지 않습니다: " + number);
        }
    }

    public int getNumber() {
        return number;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        LottoNumber that = (LottoNumber)o;
        return number == that.number;
    }

    @Override
    public int hashCode() {
        return Objects.hash(number);
    }

    private static class LottoNumberCache {
        private static final Map<Integer, LottoNumber> cache = new HashMap<>();

        static {
            for (int number = MIN_NUMBER; number <= MAX_NUMBER; ++number) {
                cache.put(number, new LottoNumber(number));
            }
        }

        static LottoNumber get(int number) {
            return cache.get(number);
        }
    }
}
