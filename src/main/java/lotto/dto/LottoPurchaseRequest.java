package lotto.dto;

public class LottoPurchaseRequest {
    private Integer moneyAmount;
    private Integer manualPurchaseAmount;
    private LottoTicketsRequest lottoTicketsRequest;

    public LottoPurchaseRequest(final Integer moneyAmount, final Integer manualPurchaseAmount,
            final LottoTicketsRequest lottoTicketsRequest) {
        this.moneyAmount = moneyAmount;
        this.manualPurchaseAmount = manualPurchaseAmount;
        this.lottoTicketsRequest = lottoTicketsRequest;
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

    public LottoTicketsRequest getLottoTicketsRequest() {
        return lottoTicketsRequest;
    }

    public void setLottoTicketsRequest(final LottoTicketsRequest lottoTicketsRequest) {
        this.lottoTicketsRequest = lottoTicketsRequest;
    }
}
