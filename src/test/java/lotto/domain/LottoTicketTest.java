package lotto.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class LottoTicketTest {

    private LottoTicket lottoTicket;

    @BeforeEach
    void setUp() {
        lottoTicket = LottoTicketUtil.getLottoTicket(1);
    }

    @Test
    void validate_Size_Less() {
        List<LottoNumber> ticket = Arrays.asList(
            LottoNumber.from(1),
            LottoNumber.from(2),
            LottoNumber.from(3),
            LottoNumber.from(4),
            LottoNumber.from(5)
        );

        assertThatThrownBy(() -> LottoTicket.from(ticket)).isInstanceOf(IllegalArgumentException.class);
    }
    
    @Test
    void validate_Size_More() {
        List<LottoNumber> ticket = Arrays.asList(
            LottoNumber.from(1),
            LottoNumber.from(2),
            LottoNumber.from(3),
            LottoNumber.from(4),
            LottoNumber.from(5),
            LottoNumber.from(6),
            LottoNumber.from(7)
        );

        assertThatThrownBy(() -> LottoTicket.from(ticket)).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void validate_Duplicate_Number() {
        List<LottoNumber> ticket = Arrays.asList(
            LottoNumber.from(1),
            LottoNumber.from(1),
            LottoNumber.from(3),
            LottoNumber.from(4),
            LottoNumber.from(5),
            LottoNumber.from(6)
        );

        assertThatThrownBy(() -> LottoTicket.from(ticket)).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void contains_True() {
        assertThat(lottoTicket.contains(LottoNumber.from(1))).isTrue();
    }

    @Test
    void contains_False() {
        assertThat(lottoTicket.contains(LottoNumber.from(7))).isFalse();
    }

    @Test
    void matchCount() {
        LottoTicket comparisonValue = LottoTicketUtil.getLottoTicket(4);

        assertThat(lottoTicket.matchCount(comparisonValue)).isEqualTo(3);
    }
}