package lotto.controller;

import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils;
import lotto.domain.*;
import lotto.view.InputView;
import lotto.view.OutputView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class Controller {

    private static final Controller controller = new Controller();

    private Controller() {}

    public static Controller getInstance() {
        return controller;
    }

    public void execute() {
        try {
            Money money = Money.from(InputView.inputMoney());
            LottoCount manual = LottoCount.from(InputView.inputManualCount());
            LottoCount auto = LottoCount.from(manual.calculateAutoCount(money));

            LottoTickets lottoTickets = createLottoTickets(manual, auto);
            OutputView.printLottoTickets(manual, auto, lottoTickets);

            WinningLotto winningLotto = createWinningLotto();
            LottoResult lottoResult = LottoResult.of(lottoTickets.matchCountAll(winningLotto) ,money);
            OutputView.printLottoResult(lottoResult);
        } catch (IllegalArgumentException | NullPointerException e) {
            System.err.println(e.toString());
        }
    }

    private LottoTickets createLottoTickets(LottoCount manual, LottoCount auto) {
        List<LottoTicket> manualLottoTickets = new ArrayList<>();
        if (manual.isNotZero()) {
            manualLottoTickets = createManualTickets(manual);
        }
        List<LottoTicket> autoLottoTickets = createAutoTickets(auto);
        return LottoTickets.of(manualLottoTickets, autoLottoTickets);
    }

    private List<LottoTicket> createManualTickets(LottoCount manual) {
        List<String> manualTickets = InputView.inputManualLotto(manual);
        LottoTicketFactory manualFactory = ManualLottoTicketFactory.from(manualTickets);
        return Collections.unmodifiableList(manualFactory.create());
    }

    private List<LottoTicket> createAutoTickets(LottoCount auto) {
        LottoTicketFactory autoFactory = AutoLottoTicketFactory.from(auto);
        return Collections.unmodifiableList(autoFactory.create());
    }

    private WinningLotto createWinningLotto() {
        String winningNumbers = InputView.inputWinningNumber();
        int bonusBall = InputView.inputBonusBall();
        return WinningLotto.of(winningNumbers, bonusBall);
    }
}