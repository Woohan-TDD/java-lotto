package lotto.dto;

import java.util.List;

import lotto.domain.lottery.LottoNumber;
import lotto.domain.lottery.LottoTicket;
import lotto.domain.lottery.WinningLotto;

public class LottoResultRequest {
    private ManualLottoTicketsRequest manualLottoTicketsRequest;
    private int totalSpendMoney;
    private List<Integer> winningNumbers;
    private Integer bonusNumber;

    public LottoResultRequest(final ManualLottoTicketsRequest manualLottoTicketsRequest, final int totalSpendMoney,
            final List<Integer> winningNumbers, final Integer bonusNumber) {
        this.manualLottoTicketsRequest = manualLottoTicketsRequest;
        this.totalSpendMoney = totalSpendMoney;
        this.winningNumbers = winningNumbers;
        this.bonusNumber = bonusNumber;
    }

    public List<LottoTicket> toLottoTicketsEntity() {
        return manualLottoTicketsRequest.toEntity();
    }

    public WinningLotto toWinningLottoEntity() {
        return new WinningLotto(LottoTicket.ofNumbers(winningNumbers), LottoNumber.valueOf(bonusNumber));
    }

    public ManualLottoTicketsRequest getManualLottoTicketsRequest() {
        return manualLottoTicketsRequest;
    }

    public void setManualLottoTicketsRequest(final ManualLottoTicketsRequest manualLottoTicketsRequest) {
        this.manualLottoTicketsRequest = manualLottoTicketsRequest;
    }

    public int getTotalSpendMoney() {
        return totalSpendMoney;
    }

    public void setTotalSpendMoney(final int totalSpendMoney) {
        this.totalSpendMoney = totalSpendMoney;
    }

    public List<Integer> getWinningNumbers() {
        return winningNumbers;
    }

    public void setWinningNumbers(final List<Integer> winningNumbers) {
        this.winningNumbers = winningNumbers;
    }

    public Integer getBonusNumber() {
        return bonusNumber;
    }

    public void setBonusNumber(final Integer bonusNumber) {
        this.bonusNumber = bonusNumber;
    }
}
