package dto;

import static java.util.stream.Collectors.*;

import java.util.List;

import domain.lotto.ticket.Lotto;
import domain.lotto.ticket.LottoNumber;

public class LottoDto {
	private List<Integer> lottoNumbers;

	public LottoDto(List<Integer> lottoNumbers) {
		this.lottoNumbers = lottoNumbers;
	}

	public static LottoDto of(Lotto lotto) {
		return lotto.getLotto()
			.stream()
			.map(LottoNumber::getLottoNumber)
			.collect(collectingAndThen(toList(), LottoDto::new));
	}

	public List<Integer> getLottoNumbers() {
		return lottoNumbers;
	}
}
