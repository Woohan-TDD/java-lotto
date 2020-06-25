package lotto.view;

import lotto.domain.LottoCount;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class InputView {
    private static final Scanner scanner = new Scanner(System.in);

    public static long inputMoney() {
        System.out.println("구입금액을 입력해 주세요.");
        return Long.parseLong(scanner.nextLine());
    }

    public static int inputManualCount() {
        System.out.println("수동으로 구매할 로또 수를 입력해 주세요.");
        return Integer.parseInt(scanner.nextLine());
    }

    public static List<String> inputManualLotto(LottoCount manualLottoCount) {
        System.out.println("수동으로 구매할 번호를 입력해 주세요.");
        List<String> lottoTickets = new ArrayList<>();
        for (int i = 0; i < manualLottoCount.getCount(); i++) {
            lottoTickets.add(scanner.nextLine());
        }
        return lottoTickets;
    }

    public static String inputWinningNumber() {
        System.out.println("지난 주 당첨 번호를 입력해 주세요.");
        return scanner.nextLine();
    }

    public static int inputBonusBall() {
        System.out.println("보너스 볼을 입력해 주세요.");
        return Integer.parseInt(scanner.nextLine());
    }
}