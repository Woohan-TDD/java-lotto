package lotto.dto;

import static java.util.stream.Collectors.collectingAndThen;
import static java.util.stream.Collectors.toList;

import java.util.List;
import java.util.stream.Stream;

import lotto.domain.lottery.LottoNumber;
import lotto.domain.lottery.LottoTicket;

public class LottoTicketRequest {
    private static final String DELIMITER = ",";

    private List<Integer> lottoNumbers;

    public LottoTicketRequest(final List<Integer> lottoNumbers) {
        this.lottoNumbers = lottoNumbers;
    }

    public static LottoTicketRequest fromString(final String manualNumber) {
        return Stream.of(manualNumber.split(DELIMITER))
                .map(number -> Integer.parseInt(number.trim()))
                .collect(collectingAndThen(toList(), LottoTicketRequest::new));
    }

    public LottoTicket toEntity() {
        return lottoNumbers.stream()
                .map(LottoNumber::valueOf)
                .collect(collectingAndThen(toList(), LottoTicket::new));
    }

    public List<Integer> getLottoNumbers() {
        return lottoNumbers;
    }

    public void setLottoNumbers(final List<Integer> lottoNumbers) {
        this.lottoNumbers = lottoNumbers;
    }
}
