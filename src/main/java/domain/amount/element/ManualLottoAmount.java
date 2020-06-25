package domain.amount.element;

import java.util.Objects;

import domain.amount.LottoMoney;

public final class ManualLottoAmount extends Amount {
	private ManualLottoAmount(int amount) {
		super(amount);
	}

	public static ManualLottoAmount of(int manualAmount, LottoMoney lottoMoney) {
		Objects.requireNonNull(lottoMoney);

		if (lottoMoney.isExcessLottoAmount(manualAmount)) {
			throw new IllegalArgumentException(
				"수동로또 구매 갯수가 금액을 초과했습니다. LottoMoney" + lottoMoney + " manualLottoAmount=" + manualAmount);
		}
		return new ManualLottoAmount(manualAmount);
	}
}
