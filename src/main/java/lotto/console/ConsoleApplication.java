package lotto.console;

import lotto.console.controller.LottoController;

public class ConsoleApplication {
	public static void main(String[] args) {
		LottoController lottoController = new LottoController();
		lottoController.run();
	}
}