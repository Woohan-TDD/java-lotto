package domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static domain.LottoNumber.MAX_LOTTO_NUMBER;
import static domain.LottoNumber.MIN_LOTTO_NUMBER;

public class LottoNumbers {
    private static LottoNumbers lottoNumbersInstance;
    private final List<LottoNumber> lottoNumbers = new ArrayList<>();

    private LottoNumbers() {
        for (int number = MIN_LOTTO_NUMBER; number <= MAX_LOTTO_NUMBER; number++) {
            lottoNumbers.add(new LottoNumber(number));
        }
    }

    public static LottoNumbers newLottoNumbers() {
        if (lottoNumbersInstance == null) {
            lottoNumbersInstance = new LottoNumbers();
        }
        return lottoNumbersInstance;
    }

    public LottoNumber valueOf(int number) {
        return lottoNumbers.stream()
                .filter(lottoNumber -> lottoNumber.isLottoNumber(number))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("로또 범위 내의 숫자가 아닙니다."));
    }

    public void shuffleLottoNumbers() {
        Collections.shuffle(lottoNumbers);
    }


    public List<LottoNumber> getLottoNumbers() {
        return Collections.unmodifiableList(lottoNumbers);
    }
}
