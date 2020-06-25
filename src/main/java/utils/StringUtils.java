package utils;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class StringUtils {

    private static final String LOTTO_NUMBER_DELIMITER = ",";
    private static final String EMPTY = "";
    private static final String BLANK = " ";

    public static int parseInt(String string) {
        try {
            return Integer.parseInt(string);
        } catch (NumberFormatException e) {
            System.out.println("숫자만 입력 해 주세요.");
            throw new NumberFormatException("잘못된 문자 입력: " + string);
        }
    }

    public static List<Integer> splitToInteger(String input) {
        input = input.replace(BLANK, EMPTY);
        if (input.isEmpty()) {
            throw new IllegalArgumentException(",를 기준으로 숫자를 입력해야 합니다.");
        }
        try {
            return Arrays.stream(input.split(LOTTO_NUMBER_DELIMITER))
                    .map(StringUtils::parseInt)
                    .collect(Collectors.toList());
        } catch (Exception e) {
            throw new IllegalArgumentException(",를 기준으로 숫자를 입력해야 합니다.");
        }
    }
}
