package view;

import utils.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class InputView {

    private static final Scanner SCANNER = new Scanner(System.in);

    public int inputMoney() {
        System.out.println("구입 금액을 입력해주세요.(숫자만 입력)");
        return StringUtils.parseInt(SCANNER.nextLine());
    }

    public int inputManualCount() {
        System.out.println("구매할 수동 로또 개수를 입력해주세요.(숫자만 입력)");
        return StringUtils.parseInt(SCANNER.nextLine());
    }

    public List<List<Integer>> inputManualLottoNumbers(int manualCount) {
        List<List<Integer>> manualLottoNumbers = new ArrayList<>();
        System.out.println("수동으로 구매할 번호를 입력해 주세요.");
        for (int i = 0; i < manualCount; i++) {
            manualLottoNumbers.add(inputLottoNumber());
        }
        return manualLottoNumbers;
    }

    public List<Integer> inputWinningNumber() {
        System.out.println("지난 주 당첨 번호를 입력해주세요.");
        return inputLottoNumber();
    }

    private List<Integer> inputLottoNumber() {
        return StringUtils.splitToInteger(SCANNER.nextLine());
    }

    public int inputBonusNumber() {
        System.out.println("보너스 번호를 입력해주세요.");
        return StringUtils.parseInt(SCANNER.nextLine());
    }
}
