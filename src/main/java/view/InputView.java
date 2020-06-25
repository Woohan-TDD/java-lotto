package view;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class InputView {
	private static final Scanner scanner = new Scanner(System.in);
	private static final String DELIMETER = ", ";

	public Long lottoMoney() {
		System.out.println("구입금액을 입력해 주세요.");
		return Long.valueOf(scanner.nextLine());
	}

	public int manualLottoAmount() {
		System.out.println("수동으로 구매할 로또 수를 입력해 주세요.");
		return Integer.parseInt(scanner.nextLine());
	}

	public List<Integer> manualLotto() {
		return inputLotto();
	}

	public List<Integer> winningLotto() {
		System.out.println("지난 주 당첨 번호를 입력해 주세요.");
		return inputLotto();
	}

	public int bonusNumber() {
		System.out.println("보너스 볼을 입력해 주세요.");
		return Integer.parseInt(scanner.next());
	}

	private List<Integer> inputLotto() {
		return Arrays.stream(scanner.nextLine().split(DELIMETER))
			.map(Integer::parseInt)
			.collect(Collectors.toList());
	}
}
