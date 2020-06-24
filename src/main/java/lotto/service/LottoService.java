package lotto.service;

import java.util.List;

import lotto.domain.lottery.LottoTicket;
import lotto.domain.lottery.LottoTicketMachine;
import lotto.domain.lottery.LottoTicketMachineFactory;
import lotto.domain.lottery.LottoTickets;
import lotto.domain.lottery.MatchResult;
import lotto.domain.lottery.WinningLotto;
import lotto.domain.purchase.InvalidPurchaseAmountException;
import lotto.domain.purchase.Money;
import lotto.domain.purchase.PurchaseAmount;
import lotto.dto.LottoPurchaseRequest;
import lotto.dto.LottoPurchaseResponse;
import lotto.dto.LottoResultRequest;
import lotto.dto.LottoResultResponse;
import lotto.dto.LottoTicketResponse;

public class LottoService {
    public LottoService() {
    }

    public LottoPurchaseResponse purchaseTickets(final LottoPurchaseRequest lottoPurchaseRequest) {
        int totalSpendMoney = lottoPurchaseRequest.getMoneyAmount();
        Money money = new Money(totalSpendMoney);
        PurchaseAmount purchaseAmount = new PurchaseAmount(lottoPurchaseRequest.getManualPurchaseAmount());
        List<LottoTicket> lottoTickets = lottoPurchaseRequest.getLottoTicketsRequest().toEntity();

        validateManualAmount(purchaseAmount, lottoTickets);

        return generateTickets(money, lottoTickets);
    }

    public LottoResultResponse calculateStatics(final LottoResultRequest lottoResultRequest) {
        List<LottoTicket> lottoTickets = lottoResultRequest.toLottoTicketsEntity();
        WinningLotto winningLotto = lottoResultRequest.toWinningLottoEntity();

        MatchResult matchResult = winningLotto.matchAll(lottoTickets);
        return LottoResultResponse.fromMatchResultAndTotalSpendMoney(matchResult,
                lottoResultRequest.getTotalSpendMoney());
    }

    private LottoPurchaseResponse generateTickets(final Money money, final List<LottoTicket> lottoTickets) {
        LottoTicketMachine manualLottoTicketMachine = LottoTicketMachineFactory.createManualTicketMachine(lottoTickets);
        LottoTicketMachine autoLottoTicketMachine = LottoTicketMachineFactory.createAutoTicketMachine();

        LottoTickets manualTickets = manualLottoTicketMachine.createTickets(money);
        LottoTickets autoTickets = autoLottoTicketMachine.createTickets(money);
        LottoTickets issuedTicket = autoTickets.concat(manualTickets);

        return new LottoPurchaseResponse(LottoTicketResponse.fromLottoTickets(issuedTicket), manualTickets.size(),
                autoTickets.size());
    }

    private void validateManualAmount(final PurchaseAmount purchaseAmount, final List<LottoTicket> lottoTickets) {
        if (purchaseAmount.isNotSameAmount(lottoTickets.size())) {
            throw new InvalidPurchaseAmountException("수동 구입 개수와 입력하신 수동 로또의 개수가 일치하지 않습니다.");
        }
    }
}
