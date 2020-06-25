package view;

import static java.util.stream.Collectors.*;

import java.util.List;
import java.util.Objects;

import dto.LottoDto;
import dto.RankResultDto;

public class OutputView {
	public void printLottoCount(int manualAmount, int autoAmount) {
		System.out.println("수동으로 " + manualAmount + "장, 자동으로 " + autoAmount + "장 구매하셨습니다.");
	}

	public void printLotto(List<LottoDto> lottoDtos) {
		for (LottoDto lottoDto : lottoDtos) {
			List<String> lottoNumbers = lottoDto.getLottoNumbers().stream()
				.map(Objects::toString)
				.collect(toList());
			System.out.println("[" + String.join(", ", lottoNumbers) + "]");
		}
	}

	public void printWinningRate(int calculateWinning) {
		System.out.printf("총 수익률은 %d%%입니다.\n", calculateWinning);
	}

	public void printRankResults(List<RankResultDto> rankResults) {
		for (RankResultDto rankResult : rankResults) {
			System.out.printf("%d개 일치", rankResult.getMatchCount());
			if (rankResult.isNeedBonus()) {
				System.out.print(" , 보너스 볼 일치");
			}
			System.out.printf(" (%d원)- %d개\n", rankResult.getWinning(), rankResult.getTotalCount());
		}
	}
}
