package lotto.controller;

import static java.util.stream.Collectors.collectingAndThen;
import static java.util.stream.Collectors.toList;

import java.util.List;

import lotto.dto.LottoPurchaseRequest;
import lotto.dto.LottoPurchaseResponse;
import lotto.dto.LottoResultRequest;
import lotto.dto.LottoResultResponse;
import lotto.dto.LottoTicketResponse;
import lotto.dto.ManualLottoTicketsRequest;
import lotto.service.LottoService;
import lotto.view.InputView;
import lotto.view.OutputView;

public class LottoController {
    private final LottoService lottoService;
    private final InputView inputView;
    private final OutputView outputView;

    public LottoController(final LottoService lottoService, final InputView inputView, final OutputView outputView) {
        this.lottoService = lottoService;
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void run() {
        LottoPurchaseRequest lottoPurchaseRequest = inputView.inputLottoPurchaseRequest();
        List<LottoTicketResponse> lottoTicketResponse = purchaseLotto(lottoPurchaseRequest);

        LottoResultRequest lottoResultRequest = generateLottoResultRequest(lottoPurchaseRequest, lottoTicketResponse);
        calculateResult(lottoResultRequest);
    }

    private List<LottoTicketResponse> purchaseLotto(final LottoPurchaseRequest lottoPurchaseRequest) {
        LottoPurchaseResponse lottoPurchaseResponse = lottoService.purchaseTickets(lottoPurchaseRequest);
        outputView.printPurchaseLottoTickets(lottoPurchaseResponse);
        return lottoPurchaseResponse.getLottoTicketResponse();
    }

    private LottoResultRequest generateLottoResultRequest(final LottoPurchaseRequest lottoPurchaseRequest,
            final List<LottoTicketResponse> lottoTicketRespons) {
        ManualLottoTicketsRequest manualLottoTicketsRequest = lottoTicketRespons.stream()
                .map(LottoTicketResponse::getNumbers)
                .collect(collectingAndThen(toList(), ManualLottoTicketsRequest::listOf));
        return inputView.inputLottoResultRequest(lottoPurchaseRequest.getMoneyAmount(),
                manualLottoTicketsRequest);
    }

    private LottoResultResponse calculateResult(final LottoResultRequest lottoResultRequest) {
        LottoResultResponse lottoResultResponse = lottoService.calculateStatics(lottoResultRequest);
        outputView.printResult(lottoResultResponse);
        return lottoResultResponse;
    }
}
