package lotto.view;

import lotto.domain.*;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class OutputView {

    public static void printLottoTickets(LottoCount manual, LottoCount auto, LottoTickets lottoTickets) {
        System.out.printf("수동으로 %d장, 자동으로 %d개를 구매했습니다.\n", manual.getCount(), auto.getCount());
        for (LottoTicket lottoTicket : lottoTickets.getLottoTickets()) {
            printLottoTicket(lottoTicket);
        }
    }

    private static void printLottoTicket(LottoTicket lottoTicket) {
        System.out.println(lottoTicket.toString());
    }

    public static void printLottoResult(LottoResult lottoResult) {
        System.out.println("당첨 통계\n---------");
        printRankResult(lottoResult);
        printYield(lottoResult);
    }

    private static void printRankResult(LottoResult lottoResult) {
        List<RankType> types = Arrays.stream(RankType.values())
                .filter(type -> type != RankType.NONE)
                .collect(Collectors.toList());

        for (RankType type : types) {
            printRankType(type);
            printRankPrize(type);
            printRankCount(lottoResult, type);
        }
    }

    private static void printRankType(RankType type) {
        System.out.print(type.getMatch() + "개 일치");
    }

    private static void printRankPrize(RankType type) {
        if (type == RankType.SECOND) {
            System.out.print(", 보너스 볼 일치(" + type.getPrize() + "원)- ");
            return ;
        }
        System.out.print("(" + type.getPrize() + "원)- ");
    }

    private static void printRankCount(LottoResult lottoResult, RankType type) {
        System.out.println(lottoResult.getRankCount(type) + "개");
    }

    private static void printYield(LottoResult lottoResult) {
        System.out.println("총 수익률은 " + lottoResult.getYield() + "%입니다.");
    }
}