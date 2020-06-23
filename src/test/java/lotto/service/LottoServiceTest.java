package lotto.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import lotto.dto.LottoPurchaseRequest;
import lotto.dto.LottoPurchaseResponse;
import lotto.dto.ManualLottoTicketsRequest;

class LottoServiceTest {
    @DisplayName("금액과 수동 로또 개수를 입력받아 로또 생성")
    @Test
    void createLottoNumber() {
        LottoService lottoService = new LottoService();
        ManualLottoTicketsRequest manualLottoTicketsRequest = ManualLottoTicketsRequest.listOf(
                Arrays.asList(
                        Arrays.asList(1, 2, 3, 4, 5, 6),
                        Arrays.asList(1, 2, 3, 4, 5, 6)
                )
        );
        LottoPurchaseRequest lottoPurchaseRequest = new LottoPurchaseRequest(5_000, 2, manualLottoTicketsRequest);

        LottoPurchaseResponse lottoTicketResponse = lottoService.purchaseTickets(lottoPurchaseRequest);

        assertThat(lottoTicketResponse.getLottoTicketResponse().size()).isEqualTo(5);
        assertThat(lottoTicketResponse.getManualAmount()).isEqualTo(2);
        assertThat(lottoTicketResponse.getAutoAmount()).isEqualTo(3);
    }
}
