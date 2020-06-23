package lotto.domain.lottery;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class LottoNumber implements Comparable<LottoNumber> {
    public static final int MIN_NUMBER = 1;
    public static final int MAX_NUMBER = 45;

    private final int number;

    private LottoNumber(final int number) {
        validateNumberRange(number);
        this.number = number;
    }

    public static LottoNumber valueOf(final int number) {
        validateNumberRange(number);
        return LottoNumberCache.get(number);
    }

    public static LottoNumber valueOf(final String number) {
        Objects.requireNonNull(number, "입력한 값이 존재하지 않습니다.");
        return valueOf(Integer.parseInt(number.trim()));
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
    public boolean equals(final Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        final LottoNumber that = (LottoNumber)o;
        return number == that.number;
    }

    @Override
    public int hashCode() {
        return Objects.hash(number);
    }

    @Override
    public int compareTo(final LottoNumber that) {
        return Integer.compare(this.number, that.number);
    }

    private static class LottoNumberCache {
        private static final Map<Integer, LottoNumber> cache = new HashMap<>();

        static {
            for (int number = MIN_NUMBER; number <= MAX_NUMBER; ++number) {
                cache.put(number, new LottoNumber(number));
            }
        }

        static LottoNumber get(final int number) {
            return cache.get(number);
        }
    }
}
