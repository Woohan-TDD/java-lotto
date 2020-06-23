package lotto.dto;

public class LottoPurchaseRequest {
    private Integer moneyAmount;
    private Integer manualPurchaseAmount;
    private ManualLottoTicketsRequest manualLottoTicketsRequest;

    public LottoPurchaseRequest(final Integer moneyAmount, final Integer manualPurchaseAmount,
            final ManualLottoTicketsRequest manualLottoTicketsRequest) {
        this.moneyAmount = moneyAmount;
        this.manualPurchaseAmount = manualPurchaseAmount;
        this.manualLottoTicketsRequest = manualLottoTicketsRequest;
    }

    public Integer getMoneyAmount() {
        return moneyAmount;
    }

    public void setMoneyAmount(final Integer moneyAmount) {
        this.moneyAmount = moneyAmount;
    }

    public Integer getManualPurchaseAmount() {
        return manualPurchaseAmount;
    }

    public void setManualPurchaseAmount(final Integer manualPurchaseAmount) {
        this.manualPurchaseAmount = manualPurchaseAmount;
    }

    public ManualLottoTicketsRequest getManualLottoTicketsRequest() {
        return manualLottoTicketsRequest;
    }

    public void setManualLottoTicketsRequest(final ManualLottoTicketsRequest manualLottoTicketsRequest) {
        this.manualLottoTicketsRequest = manualLottoTicketsRequest;
    }
}
