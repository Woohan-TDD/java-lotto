package view;

import java.util.Map;
import java.util.stream.Collectors;

import domain.amount.LottoAmount;
import domain.lotto.Lotto;
import domain.lotto.LottoTickets;
import domain.lotto.number.LottoNumber;
import domain.statistics.Rank;

public class OutputView {
	private static final String DELIMITER = ", ";

	public void showManualLotto() {
		System.out.println("수동으로 구매할 번호를 입력해 주세요.");
	}

	public void showPurchasedLotto(LottoAmount lottoAmount, LottoTickets lottoTickets) {
		System.out.printf("수동으로 %d장, 자동으로 %d개를 구매했습니다.\n", lottoAmount.getManualLottoAmount().getAmount(),
			lottoAmount.getAutoLottoAmount().getAmount());

		for (Lotto lotto : lottoTickets.getTickets()) {
			String numbers = lotto.getLottoNumbers()
				.stream()
				.map(LottoNumber::getNumber)
				.map(String::valueOf)
				.collect(Collectors.joining(DELIMITER));
			System.out.printf("[%s]\n", numbers);
		}

		System.out.println();
	}

	public void showWinningStatistics(Map<Rank, Integer> countByRank) {
		System.out.println("당첨 통계");
		System.out.println("---------");
		for (Rank rank : Rank.values()) {
			System.out.printf("%d개 일치 (%d원)- %d개\n", rank.getMatch(), rank.getMoney(),
				countByRank.getOrDefault(rank, 0));
		}
	}

	public void showProfit(double profit) {
		System.out.printf("총 수익률은 %.2f%%입니다.\n", profit);
	}
}
