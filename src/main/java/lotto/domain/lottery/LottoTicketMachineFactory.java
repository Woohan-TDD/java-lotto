package lotto.domain.lottery;

import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class LottoTicketMachineFactory {
    private LottoTicketMachineFactory() {
        throw new AssertionError();
    }

    public static LottoTicketMachine createManualTicketMachine(List<LottoTicket> lottoTickets) {
        return new ManualLottoTicketMachine(lottoTickets);
    }

    public static LottoTicketMachine createAutoTicketMachine() {
        return new AutoLottoTicketMachine(createRandom());
    }

    private static Random createRandom() {
        return ThreadLocalRandom.current();
    }
}
