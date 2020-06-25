package lotto.dto;

import java.util.List;

import lotto.domain.lottery.LottoNumber;
import lotto.domain.lottery.LottoTicket;
import lotto.domain.lottery.WinningLotto;

public class LottoResultRequest {
    private LottoTicketsRequest lottoTicketsRequest;
    private int totalSpendMoney;
    private List<Integer> winningNumbers;
    private Integer bonusNumber;

    public LottoResultRequest(final LottoTicketsRequest lottoTicketsRequest, final int totalSpendMoney,
            final List<Integer> winningNumbers, final Integer bonusNumber) {
        this.lottoTicketsRequest = lottoTicketsRequest;
        this.totalSpendMoney = totalSpendMoney;
        this.winningNumbers = winningNumbers;
        this.bonusNumber = bonusNumber;
    }

    public List<LottoTicket> toLottoTicketsEntity() {
        return lottoTicketsRequest.toEntity();
    }

    public WinningLotto toWinningLottoEntity() {
        return new WinningLotto(LottoTicket.ofNumbers(winningNumbers), LottoNumber.valueOf(bonusNumber));
    }

    public LottoTicketsRequest getLottoTicketsRequest() {
        return lottoTicketsRequest;
    }

    public void setLottoTicketsRequest(final LottoTicketsRequest lottoTicketsRequest) {
        this.lottoTicketsRequest = lottoTicketsRequest;
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
