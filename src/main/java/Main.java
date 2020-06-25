import controller.LottoController;
import domain.lotto.strategy.RandomLottoCreateStrategy;
import view.InputView;
import view.OutputView;

public class Main {
	public static void main(String[] args) {
		new LottoController(new InputView(), new OutputView(), new RandomLottoCreateStrategy()).run();
	}
}
