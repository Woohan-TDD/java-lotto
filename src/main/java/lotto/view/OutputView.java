package lotto.view;

import java.util.stream.Collectors;

import lotto.dto.LottoPurchaseResponse;
import lotto.dto.LottoRankResponse;
import lotto.dto.LottoResultResponse;
import lotto.dto.LottoTicketResponse;

public class OutputView {
    OutputView() {
    }

    public void printPurchaseLottoTickets(final LottoPurchaseResponse lottoPurchaseResponse) {
        printPurchaseAmount(lottoPurchaseResponse.getManualAmount(), lottoPurchaseResponse.getAutoAmount());
        for (LottoTicketResponse lottoTicketResponse : lottoPurchaseResponse.getLottoTicketResponse()) {
            printPurchaseLottoTicket(lottoTicketResponse);
        }
    }

    public void printResult(final LottoResultResponse lottoResultResponse) {
        System.out.println("\n당첨 통계");
        System.out.println("--------------------------------");
        for (LottoRankResponse lottoRankResponse : lottoResultResponse.getLottoRankResponses()) {
            System.out.printf("%d개 일치 (%d원) - %d개\n", lottoRankResponse.getNumberOfMatch(),
                    lottoRankResponse.getPrize(),
                    lottoRankResponse.getCount());
        }
        System.out.println("--------------------------------");
        System.out.printf("총 수익률은 %d%% 입니다.\n", lottoResultResponse.getProfitRate());
    }

    private void printPurchaseAmount(final int manualAmount, final int autoAmount) {
        System.out.printf("\n수동으로 %d장, 자동으로 %d장 구매했습니다.\n", manualAmount, autoAmount);
    }

    private void printPurchaseLottoTicket(final LottoTicketResponse lottoTicketResponse) {
        String ticketContent = lottoTicketResponse.getNumbers()
                .stream()
                .map(String::valueOf)
                .collect(Collectors.joining(", ", "[", "]"));
        System.out.println(ticketContent);
    }
}
