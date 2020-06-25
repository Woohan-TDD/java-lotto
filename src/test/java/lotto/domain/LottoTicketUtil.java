package lotto.domain;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class LottoTicketUtil {

    private static final Map<Integer, LottoTicket> lotto = new HashMap<>();;

    static {
        for (int i = 1; i < 5; i++) {
            lotto.put(i, LottoTicket.from(createTicket(i, i + 5)));
        }
    }

    private static List<LottoNumber> createTicket(int start, int end) {
        List<Integer> numbers = IntStream.range(start, end + 1)
                                        .boxed()
                                        .collect(Collectors.toList());
        return numbers.stream()
                .map(LottoNumber::from)
                .collect(Collectors.toList());
    }

    public static LottoTicket getLottoTicket(int number) {
        return lotto.get(number);
    }
}
