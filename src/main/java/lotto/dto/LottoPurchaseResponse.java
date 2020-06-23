package lotto.dto;

import java.util.List;

public class LottoPurchaseResponse {
    private final List<LottoTicketResponse> lottoTicketResponse;
    private int manualAmount;
    private int autoAmount;

    public LottoPurchaseResponse(final List<LottoTicketResponse> lottoTicketResponse, final int manualAmount,
            final int autoAmount) {
        this.lottoTicketResponse = lottoTicketResponse;
        this.manualAmount = manualAmount;
        this.autoAmount = autoAmount;
    }

    public List<LottoTicketResponse> getLottoTicketResponse() {
        return lottoTicketResponse;
    }

    public int getManualAmount() {
        return manualAmount;
    }

    public void setManualAmount(final int manualAmount) {
        this.manualAmount = manualAmount;
    }

    public int getAutoAmount() {
        return autoAmount;
    }

    public void setAutoAmount(final int autoAmount) {
        this.autoAmount = autoAmount;
    }
}
