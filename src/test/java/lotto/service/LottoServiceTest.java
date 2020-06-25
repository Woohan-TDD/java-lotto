package lotto.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.Arrays;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import lotto.domain.purchase.InvalidPurchaseAmountException;
import lotto.dto.LottoPurchaseRequest;
import lotto.dto.LottoPurchaseResponse;
import lotto.dto.LottoResultRequest;
import lotto.dto.LottoResultResponse;
import lotto.dto.LottoTicketsRequest;

class LottoServiceTest {
    private LottoService lottoService;

    @BeforeEach
    void setUp() {
        lottoService = new LottoService();
    }

    @DisplayName("금액과 수동 로또 개수를 입력받아 로또 생성")
    @Test
    void createLottoNumber() {
        LottoTicketsRequest lottoTicketsRequest = LottoTicketsRequest.listOf(
                Arrays.asList(
                        Arrays.asList(1, 2, 3, 4, 5, 6),
                        Arrays.asList(1, 2, 3, 4, 5, 6)
                )
        );
        LottoPurchaseRequest lottoPurchaseRequest = new LottoPurchaseRequest(5_000, 2, lottoTicketsRequest);

        LottoPurchaseResponse lottoTicketResponse = lottoService.purchaseTickets(lottoPurchaseRequest);

        assertThat(lottoTicketResponse.getLottoTicketResponse().size()).isEqualTo(5);
        assertThat(lottoTicketResponse.getManualAmount()).isEqualTo(2);
        assertThat(lottoTicketResponse.getAutoAmount()).isEqualTo(3);
    }

    @DisplayName("수동 구입 개수와 입력한 수동 로또의 수가 일치하지 않는 경우 예외 발생")
    @ValueSource(ints = {1, 3})
    @ParameterizedTest
    void createLottoNumber_ManualLottoAmountAndInputManualLottoSizeMismatch_ExceptionThrown(
            final int manualLottoAmount) {
        LottoTicketsRequest lottoTicketsRequest = LottoTicketsRequest.listOf(
                Arrays.asList(
                        Arrays.asList(1, 2, 3, 4, 5, 6),
                        Arrays.asList(1, 2, 3, 4, 5, 6)
                )
        );
        LottoPurchaseRequest lottoPurchaseRequest = new LottoPurchaseRequest(5_000, manualLottoAmount,
                lottoTicketsRequest);

        assertThatThrownBy(() -> lottoService.purchaseTickets(lottoPurchaseRequest))
                .isInstanceOf(InvalidPurchaseAmountException.class)
                .hasMessageContaining("수동 구입 개수와 입력하신 수동 로또의 개수가 일치하지 않습니다");
    }

    @DisplayName("로또 번호화 우승 번호를 입력하여 결과 반환")
    @Test
    void calculateStatics() {
        LottoTicketsRequest lottoTicketsRequest = LottoTicketsRequest.listOf(
                Arrays.asList(
                        Arrays.asList(1, 2, 3, 4, 5, 6),
                        Arrays.asList(1, 2, 3, 4, 5, 6)
                )
        );
        LottoResultRequest lottoResultRequest = new LottoResultRequest(lottoTicketsRequest, 50_000,
                Arrays.asList(1, 2, 3, 4, 5, 6), 7);

        LottoResultResponse lottoResultResponse = lottoService.calculateStatics(lottoResultRequest);

        assertThat(lottoResultResponse).isNotNull();
    }
}
