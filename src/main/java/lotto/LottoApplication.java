package lotto;

import lotto.controller.LottoController;
import lotto.service.LottoService;
import lotto.view.InputView;
import lotto.view.OutputView;
import lotto.view.ViewFactory;

public class LottoApplication {
    public static void main(String[] args) {
        LottoService lottoService = new LottoService();
        InputView inputView = ViewFactory.createInputView();
        OutputView outputView = ViewFactory.createOutputView();
        LottoController lottoController = new LottoController(lottoService, inputView, outputView);

        try {
            lottoController.run();
        } catch (Exception exception) {
            exception.printStackTrace();
            System.err.println("에러가 발생했습니다.");
        }
    }
}
