package lotto.view;

import java.util.Scanner;

public class ViewFactory {
    private ViewFactory() {
        throw new AssertionError();
    }

    public static OutputView createOutputView() {
        return new OutputView();
    }

    public static InputView createInputView() {
        return new InputView(createScanner());
    }

    private static Scanner createScanner() {
        return new Scanner(System.in);
    }
}
