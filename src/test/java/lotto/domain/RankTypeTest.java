package lotto.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class RankTypeTest {

    private WinningLotto winningLotto;

    @BeforeEach
    void setUp() {
        winningLotto = WinningLotto.of("1, 2, 3, 4, 5, 6", 7);
    }

    @ParameterizedTest
    @CsvSource(value = {"1, 2, 3, 4, 5, 6:FIRST",
                        "1, 2, 3, 4, 5, 7:SECOND",
                        "1, 2, 3, 4, 5, 8:THIRD",
                        "1, 2, 3, 4, 8, 9:FOURTH",
                        "1, 2, 3, 8, 9, 10:FIFTH",
                        "1, 2, 8, 9, 10, 11:NONE",
                        "1, 8, 9, 10, 11, 12:NONE",
                        "8, 9, 10, 11, 12, 13:NONE"
    }, delimiter = ':')
    void rankType_All(String input, RankType rankType) {
        ManualLottoTicketFactory factory = ManualLottoTicketFactory.from(Arrays.asList(input));
        List<LottoTicket> lottoTicket = factory.create();
        RankType type = RankType.findType(lottoTicket.get(0), winningLotto);
        assertThat(type).isEqualTo(rankType);
    }
}