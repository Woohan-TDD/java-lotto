package domain;

public class WinningLotto {
    private final Lotto winningLotto;
    private final LottoNumber bonusNumber;

    public WinningLotto(final Lotto winningLotto, final LottoNumber bonusNumber) {
        validate(winningLotto, bonusNumber);
        this.winningLotto = winningLotto;
        this.bonusNumber = bonusNumber;
    }

    private void validate(Lotto winningLotto, LottoNumber bonusNumber) {
        if (winningLotto.contains(bonusNumber)) {
            throw new IllegalArgumentException("보너스볼은 로또 번호와 중복되면 안 됩니다.");
        }
    }

    public int calculateMatch(Lotto lotto) {
        return (int) winningLotto.getLotto()
                .stream()
                .filter(lotto::contains)
                .count();
    }

    public boolean isBonus(Lotto lotto) {
        return lotto.contains(bonusNumber);
    }
}
