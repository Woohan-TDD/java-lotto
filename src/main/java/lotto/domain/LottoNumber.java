package lotto.domain;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class LottoNumber implements Comparable<LottoNumber> {

    private static final int MINIMUM_NUMBER = 1;
    private static final int MAXIMUM_NUMBER = 45;

    private static final Map<Integer, LottoNumber> cache = new HashMap<>();

    static {
        for (int number = MINIMUM_NUMBER; number <= MAXIMUM_NUMBER; number++) {
            cache.put(number, new LottoNumber(number));
        }
    }

    private final int number;
    
    private LottoNumber(int number) {
        this.number = number;
    }
    
    public static LottoNumber from(int number) {
        validateNumberRange(number);
        return cache.get(number);
    }

    private static void validateNumberRange(int number) {
        if (number < MINIMUM_NUMBER || number > MAXIMUM_NUMBER) {
            throw new IllegalArgumentException("로또 번호는 1과 45사이 숫자만 가능합니다.");
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LottoNumber that = (LottoNumber) o;
        return number == that.number;
    }

    @Override
    public int hashCode() {
        return Objects.hash(number);
    }

    @Override
    public int compareTo(LottoNumber lottoNumber) {
        return Integer.compare(number, lottoNumber.number);
    }

    @Override
    public String toString() {
        return String.valueOf(number);
    }
}