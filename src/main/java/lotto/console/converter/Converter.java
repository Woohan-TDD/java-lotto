package lotto.console.converter;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class Converter {

	private final static String NUMBER_LIST_DELIMITER = ",";

	public static int numberConverterBy(final String value) {
		try {
			return Integer.parseInt(value);
		} catch (NumberFormatException e) {
			throw new NumberFormatException("숫자만 입력 가능합니다. value = " + value);
		}
	}

	public static List<Integer> numberListConverterBy(final String value) {
		return Collections.unmodifiableList(Arrays
			.stream(value.split(NUMBER_LIST_DELIMITER))
			.map(String::trim)
			.map(Converter::numberConverterBy)
			.collect(Collectors.toList()));
	}
}