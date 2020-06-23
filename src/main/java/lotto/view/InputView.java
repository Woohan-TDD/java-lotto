package lotto.view;

import static java.util.stream.Collectors.toList;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Stream;

import lotto.dto.LottoPurchaseRequest;
import lotto.dto.LottoResultRequest;
import lotto.dto.ManualLottoTicketsRequest;

public class InputView {
    private static final String DELIMITER = ",";
    private final Scanner scanner;

    InputView(final Scanner scanner) {
        this.scanner = scanner;
    }

    public LottoPurchaseRequest inputLottoPurchaseRequest() {
        int purchaseMoney = inputPurchaseMoney();
        System.out.println("\n수동으로 구매할 로또 수를 입력해 주세요.");
        int manualPurchaseCount = inputManualPurchaseCount();
        List<String> manualNumbers = inputManualNumbers(manualPurchaseCount);
        ManualLottoTicketsRequest manualLottoTicketsRequest = ManualLottoTicketsRequest.fromStrings(manualNumbers);
        return new LottoPurchaseRequest(purchaseMoney, manualPurchaseCount, manualLottoTicketsRequest);
    }

    public LottoResultRequest inputLottoResultRequest(final int purchaseMoney,
            final ManualLottoTicketsRequest manualLottoTicketsRequest) {
        List<Integer> winningNumbers = inputWinningNumbers();
        int bonusNumber = inputBonusNumber();
        return new LottoResultRequest(manualLottoTicketsRequest, purchaseMoney, winningNumbers, bonusNumber);
    }

    private int inputPurchaseMoney() {
        System.out.println("\n구입금액을 입력해 주세요.");
        return Integer.parseInt(scanner.nextLine());
    }

    private int inputManualPurchaseCount() {
        return Integer.parseInt(scanner.nextLine());
    }

    private List<String> inputManualNumbers(final int purchaseCount) {
        List<String> manualNumbers = new ArrayList<>();
        for (int count = 0; count < purchaseCount; ++count) {
            manualNumbers.add(inputManualNumber());
        }
        return manualNumbers;
    }

    private List<Integer> inputWinningNumbers() {
        System.out.println("\n지난 주 당첨 번호를 입력해 주세요.");
        String input = scanner.nextLine();
        return Stream.of(input.split(DELIMITER))
                .map(value -> Integer.parseInt(value.trim()))
                .collect(toList());
    }

    private int inputBonusNumber() {
        System.out.println("\n보너스 볼을 입력해 주세요.");
        return Integer.parseInt(scanner.nextLine());
    }

    private String inputManualNumber() {
        System.out.println("\n수동으로 구매할 번호를 입력해 주세요.");
        return scanner.nextLine();
    }
}
