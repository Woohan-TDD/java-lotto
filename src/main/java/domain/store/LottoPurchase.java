package domain.store;

public class LottoPurchase {
	private final Money money;
	private final int manualAmount;

	public LottoPurchase(Money money, int manualAmount) {
		validate(manualAmount);
		this.money = money;
		this.manualAmount = manualAmount;
	}

	private void validate(int manualAmount) {
		if (manualAmount < 0) {
			throw new IllegalArgumentException("수동 구매 횟수는 음수일 수 없습니다. input : " + manualAmount);
		}
	}

	public int calculateAutoAmount(long lottoPrice) {
		validate(lottoPrice);
		return money.divideBy(lottoPrice) - manualAmount;
	}

	private void validate(long lottoPrice) {
		validateExcess(lottoPrice);
		validateNegative(lottoPrice);
		validateRemainder(lottoPrice);
	}

	private void validateRemainder(long lottoPrice) {
		if (!money.isDividable(lottoPrice)) {
			throw new IllegalArgumentException("거스름돈이 남습니다. 다시 입력해주세요. input : " + lottoPrice);
		}
	}

	private void validateNegative(long lottoPrice) {
		if (lottoPrice <= 0) {
			throw new IllegalArgumentException("로또 가격은 0이하일 수 없습니다. input : " + lottoPrice);
		}
	}

	private void validateExcess(long lottoPrice) {
		if (money.isEqualToOrLessThan(lottoPrice * manualAmount)) {
			throw new IllegalArgumentException("수동 구매 횟수가 너무 많습니다. input : " + lottoPrice);
		}
	}

	public boolean isNotEqualToManualAmount(int size) {
		return manualAmount != size;
	}

	public int getManualAmount() {
		return manualAmount;
	}
}
