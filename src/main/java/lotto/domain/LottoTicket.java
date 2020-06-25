package lotto.domain;

import java.util.*;

public class LottoTicket {

    private static final int LOTTO_TICKET_SIZE = 6;

    private final List<LottoNumber> ticket;

    private LottoTicket(List<LottoNumber> ticket) {
        Objects.requireNonNull(ticket, "Null 감지");
        validateSize(ticket);
        validateDuplicateNumber(ticket);
        this.ticket = new ArrayList<>(ticket);
    }

    private static void validateSize(List<LottoNumber> ticket) {
        if (ticket.size() != LOTTO_TICKET_SIZE) {
            throw new IllegalArgumentException("로또 티켓의 사이즈는 6만 가능합니다.");
        }
    }

    private void validateDuplicateNumber(List<LottoNumber> ticket) {
        Set<LottoNumber> notDuplicatedTicket = new HashSet<>(ticket);
        if (notDuplicatedTicket.size() != LOTTO_TICKET_SIZE) {
            throw new IllegalArgumentException("중복된 번호가 있습니다.");
        }
    }

    public static LottoTicket from(List<LottoNumber> ticket) {
        return new LottoTicket(ticket);
    }

    public boolean contains(LottoNumber lottoNumber) {
        return ticket.contains(lottoNumber);
    }

    public int matchCount(LottoTicket lottoTicket) {
        return (int) ticket.stream()
                .filter(lottoTicket::contains)
                .count();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LottoTicket that = (LottoTicket) o;
        return Objects.equals(ticket, that.ticket);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ticket);
    }

    @Override
    public String toString() {
        return String.valueOf(ticket);
    }
}