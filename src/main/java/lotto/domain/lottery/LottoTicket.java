package lotto.domain.lottery;

import static java.util.stream.Collectors.collectingAndThen;
import static java.util.stream.Collectors.toList;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class LottoTicket {
    public static final int TOTAL_LOTTO_NUMBER = 6;
    private static final String NUMBER_DELIMITER = ",";

    private final List<LottoNumber> lottoNumbers;

    public LottoTicket(final List<LottoNumber> lottoNumbers) {
        validateLottoNumbers(lottoNumbers);
        List<LottoNumber> copiedLottoNumbers = new ArrayList<>(lottoNumbers);
        Collections.sort(copiedLottoNumbers);
        this.lottoNumbers = copiedLottoNumbers;
    }

    public static LottoTicket ofNumbers(final List<Integer> numbers) {
        return numbers.stream()
                .map(LottoNumber::valueOf)
                .collect(collectingAndThen(toList(), LottoTicket::new));
    }

    public static LottoTicket ofComma(final String numbersWithComma) {
        return Arrays.stream(numbersWithComma.split(NUMBER_DELIMITER))
                .map(LottoNumber::valueOf)
                .collect(collectingAndThen(toList(), LottoTicket::new));
    }

    public boolean contains(final LottoNumber lottoNumber) {
        return lottoNumbers.contains(lottoNumber);
    }

    public int match(final LottoTicket that) {
        return (int)that.lottoNumbers
                .stream()
                .filter(this::contains)
                .count();
    }

    private void validateLottoNumbers(final List<LottoNumber> lottoNumbers) {
        Objects.requireNonNull(lottoNumbers, "입력받은 리스트가 존재하지 않습니다.");
        validateSize(lottoNumbers);
        validateDuplicate(lottoNumbers);
    }

    private void validateSize(final List<LottoNumber> lottoNumbers) {
        if (lottoNumbers.size() != TOTAL_LOTTO_NUMBER) {
            throw new InvalidLottoNumberException("입력받은 로또 수가 올바르지 않습니다. " +
                    "expect: " + TOTAL_LOTTO_NUMBER + ", " +
                    "actual: " + lottoNumbers.size());
        }
    }

    private void validateDuplicate(final List<LottoNumber> lottoNumbers) {
        long count = lottoNumbers.stream()
                .distinct()
                .count();

        if (count != TOTAL_LOTTO_NUMBER) {
            throw new InvalidLottoNumberException("입력받은 로또 수에 중복이 존재합니다: " + lottoNumbers);
        }
    }

    public List<LottoNumber> getLottoNumbers() {
        return Collections.unmodifiableList(lottoNumbers);
    }
}
