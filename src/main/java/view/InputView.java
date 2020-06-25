package view;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class InputView {
	private final Scanner scanner;

	public InputView(Scanner scanner) {
		this.scanner = scanner;
	}

	public long getMoney() {
		System.out.println("구입금액을 입력해 주세요.");
		return Long.parseLong(scanner.nextLine());
	}

	public int getManualAmount() {
		System.out.println("수동으로 구매할 로또 수를 입력해 주세요.");
		return Integer.parseInt(scanner.nextLine());
	}

	public List<List<Integer>> getManualLottos(int manualAmount) {
		System.out.println("수동으로 구매할 번호를 입력해 주세요.");
		List<List<Integer>> lottos = new ArrayList<>();
		for (int i = 0; i < manualAmount; i++) {
			lottos.add(getLottoNumbers());
		}
		return lottos;
	}

	public List<Integer> getWinningLotto() {
		System.out.println("지난 주 당첨 번호를 입력해 주세요.");
		return getLottoNumbers();
	}

	private List<Integer> getLottoNumbers() {
		return Arrays.stream(scanner.nextLine().split(","))
			.map(String::trim)
			.map(Integer::parseInt)
			.collect(Collectors.toList());
	}

	public int getBonusNumber() {
		System.out.println("보너스 볼을 입력해 주세요.");
		return Integer.parseInt(scanner.nextLine());
	}
}
