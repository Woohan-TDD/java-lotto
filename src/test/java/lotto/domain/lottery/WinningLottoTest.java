package lotto.domain.lottery;

import static lotto.domain.lottery.LottoTicketUtil.generateLottoTicket;
import static lotto.domain.lottery.Rank.FIRST;
import static lotto.domain.lottery.Rank.FOURTH;
import static lotto.domain.lottery.Rank.NOTHING;
import static lotto.domain.lottery.Rank.SECOND;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class WinningLottoTest {
    private LottoTicket lottoTicket;

    @BeforeEach
    void setUp() {
        lottoTicket = generateLottoTicket(1, 2, 3, 4, 5, 6);
    }

    @DisplayName("우승 번호와 보너스 번호를 입력받아 인스턴스 생성")
    @Test
    void constructor() {
        LottoNumber bonusNumber = LottoNumber.valueOf(7);

        assertThat(new WinningLotto(lottoTicket, bonusNumber)).isInstanceOf(WinningLotto.class);
    }

    @DisplayName("로또 티켓과 보너스 번호 모두 null인 경우 예외 발생")
    @Test
    void constructor_LottoTicketAndBonusNumberIsNull_ExceptionThrown() {
        assertThatThrownBy(() -> new WinningLotto(null, null))
                .isInstanceOf(NullPointerException.class);
    }

    @DisplayName("우승 번호가 null인 경우 예외 발생")
    @Test
    void constructor_LottoTicketIsNull_ExceptionThrown() {
        LottoNumber bonusNumber = LottoNumber.valueOf(7);

        assertThatThrownBy(() -> new WinningLotto(null, bonusNumber))
                .isInstanceOf(NullPointerException.class)
                .hasMessageContaining("로또 티켓이 존재하지 않습니다");
    }

    @DisplayName("보너스 번호가 null인 경우 예외 발생")
    @Test
    void constructor_BonusNumberIsNull_ExceptionThrown() {
        assertThatThrownBy(() -> new WinningLotto(lottoTicket, null))
                .isInstanceOf(NullPointerException.class)
                .hasMessageContaining("보너스 번호가 존재하지 않습니다");
    }

    @DisplayName("우승 번호에 보너스 번호가 포함되어 있는 경우 예외 발생")
    @Test
    void constructor_DuplicatedNumberExist_ExceptionThrown() {
        LottoNumber bonusNumber = LottoNumber.valueOf(6);

        assertThatThrownBy(() -> new WinningLotto(lottoTicket, bonusNumber))
                .isInstanceOf(InvalidWinningLottoException.class)
                .hasMessageContaining("우승 번호와 보너스 번호가 중복됩니다");
    }

    @DisplayName("보너스 번호 포함 여부를 확인")
    @Test
    void containsBonus() {
        LottoNumber bonusNumber = LottoNumber.valueOf(7);
        WinningLotto winningLotto = new WinningLotto(lottoTicket, bonusNumber);
        LottoTicket ticket = generateLottoTicket(1, 2, 3, 4, 5, 7);

        assertThat(winningLotto.containsBonus(ticket)).isTrue();
    }

    @DisplayName("로또 한장을 입력받아 순위 계산")
    @Test
    void match() {
        LottoNumber bonusNumber = LottoNumber.valueOf(7);
        WinningLotto winningLotto = new WinningLotto(lottoTicket, bonusNumber);
        LottoTicket ticket = generateLottoTicket(1, 2, 3, 4, 5, 6);

        assertThat(winningLotto.match(ticket)).isEqualTo(FIRST);
    }

    @DisplayName("로또 여러장을 입력받아 순위 계산")
    @Test
    void matchAll() {
        LottoNumber bonusNumber = LottoNumber.valueOf(7);
        WinningLotto winningLotto = new WinningLotto(lottoTicket, bonusNumber);

        List<LottoTicket> tickets = Arrays.asList(
                generateLottoTicket(1, 2, 3, 4, 5, 6),
                generateLottoTicket(1, 2, 3, 4, 5, 6),
                generateLottoTicket(1, 2, 3, 4, 5, 7),
                generateLottoTicket(1, 2, 3, 4, 23, 24)
        );

        MatchResult matchResult = winningLotto.matchAll(tickets);

        assertThat(matchResult.getCount(FIRST)).isEqualTo(2L);
        assertThat(matchResult.getCount(SECOND)).isEqualTo(1L);
        assertThat(matchResult.getCount(FOURTH)).isEqualTo(1L);
        assertThat(matchResult.getCount(NOTHING)).isEqualTo(0L);
    }
}
