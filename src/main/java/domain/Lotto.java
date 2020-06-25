package domain;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Lotto {
    public static final int LOTTO_LENGTH = 6;
    private static final LottoFactory lottoFactory = LottoFactory.getLottoFactory();

    private final List<LottoNumber> lotto;

    private Lotto(final List<LottoNumber> lotto) {
        validateLength(lotto);
        validateDuplication(lotto);
        this.lotto = lotto;
    }

    public static Lotto createAutoLotto() {
        return new Lotto(lottoFactory.createAutoLotto());
    }

    public static Lotto createManualLotto(List<Integer> manualLottoNumbers) {
        return new Lotto(lottoFactory.createManualLotto(manualLottoNumbers));
    }

    private void validateLength(List<LottoNumber> lotto) {
        if (lotto == null || lotto.size() != LOTTO_LENGTH) {
            throw new IllegalArgumentException("로또 한장은 6개의 숫자로 이루어져 있어야 합니다.");
        }
    }

    private void validateDuplication(List<LottoNumber> lotto) {
        Set<LottoNumber> lottoNumbers = new HashSet<>(lotto);
        if (lotto.size() != lottoNumbers.size()) {
            throw new IllegalArgumentException("로또 한장은 서로 다른 숫자로 이루어져 있어야 합니다.");
        }
    }

    public boolean contains(LottoNumber number) {
        return lotto.contains(number);
    }

    public List<LottoNumber> getLotto() {
        return Collections.unmodifiableList(lotto);
    }

    @Override
    public String toString() {
        Collections.sort(lotto);
        return lotto.toString();
    }
}
