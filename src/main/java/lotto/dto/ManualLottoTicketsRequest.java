package lotto.dto;

import static java.util.stream.Collectors.collectingAndThen;
import static java.util.stream.Collectors.toList;

import java.util.List;

import lotto.domain.lottery.LottoTicket;
import lotto.domain.lottery.LottoTickets;

public class ManualLottoTicketsRequest {
    private List<ManualLottoTicketRequest> tickets;

    public ManualLottoTicketsRequest(final List<ManualLottoTicketRequest> tickets) {
        this.tickets = tickets;
    }

    public static ManualLottoTicketsRequest fromStrings(final List<String> manualNumbers) {
        return manualNumbers.stream()
                .map(ManualLottoTicketRequest::fromString)
                .collect(collectingAndThen(toList(), ManualLottoTicketsRequest::new));
    }

    public static ManualLottoTicketsRequest listOf(final List<List<Integer>> ticketNumbers) {
        return ticketNumbers.stream()
                .map(ManualLottoTicketRequest::new)
                .collect(collectingAndThen(toList(), ManualLottoTicketsRequest::new));
    }

    public LottoTickets toLottoTicketsEntity() {
        return tickets.stream()
                .map(ManualLottoTicketRequest::toEntity)
                .collect(collectingAndThen(toList(), LottoTickets::new));
    }

    public List<LottoTicket> toEntity() {
        return tickets.stream()
                .map(ManualLottoTicketRequest::toEntity)
                .collect(toList());
    }

    public List<ManualLottoTicketRequest> getTickets() {
        return tickets;
    }

    public void setTickets(final List<ManualLottoTicketRequest> tickets) {
        this.tickets = tickets;
    }
}
