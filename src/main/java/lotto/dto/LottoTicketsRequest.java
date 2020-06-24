package lotto.dto;

import static java.util.stream.Collectors.collectingAndThen;
import static java.util.stream.Collectors.toList;

import java.util.List;

import lotto.domain.lottery.LottoTicket;
import lotto.domain.lottery.LottoTickets;

public class LottoTicketsRequest {
    private List<LottoTicketRequest> tickets;

    public LottoTicketsRequest(final List<LottoTicketRequest> tickets) {
        this.tickets = tickets;
    }

    public static LottoTicketsRequest fromStrings(final List<String> manualNumbers) {
        return manualNumbers.stream()
                .map(LottoTicketRequest::fromString)
                .collect(collectingAndThen(toList(), LottoTicketsRequest::new));
    }

    public static LottoTicketsRequest listOf(final List<List<Integer>> ticketNumbers) {
        return ticketNumbers.stream()
                .map(LottoTicketRequest::new)
                .collect(collectingAndThen(toList(), LottoTicketsRequest::new));
    }

    public LottoTickets toLottoTicketsEntity() {
        return tickets.stream()
                .map(LottoTicketRequest::toEntity)
                .collect(collectingAndThen(toList(), LottoTickets::new));
    }

    public List<LottoTicket> toEntity() {
        return tickets.stream()
                .map(LottoTicketRequest::toEntity)
                .collect(toList());
    }

    public List<LottoTicketRequest> getTickets() {
        return tickets;
    }

    public void setTickets(final List<LottoTicketRequest> tickets) {
        this.tickets = tickets;
    }
}
