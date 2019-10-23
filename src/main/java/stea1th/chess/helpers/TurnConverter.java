package stea1th.chess.helpers;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class TurnConverter {

    private static final String REGEX = "[A-Ha-h][1-8]";
    private static final Integer BOARD_SIZE = 8;
    private static final Integer LETTER_VALUE = 96;

    public static String convert(String message) {
        List<String> matched = match(message);
        if(matched == null) {
            return null;
        }
        return transformFromListToStr(matched);
    }

    private static List<String> match(String message) {
        List<String> matched = new ArrayList<>();
        Pattern pattern = Pattern.compile(REGEX);
        Matcher matcher = pattern.matcher(message);
        while (matcher.find()) {
            matched.add(matcher.group().toLowerCase());
        }
        return matched.size() == 2 ? matched : null;
    }

    private static String transformFromListToStr(List<String> list) {
        return list.stream()
                .map(TurnConverter::transform)
                .collect(Collectors.joining(":"));
    }

    private static String transform(String message) {
        return String.valueOf((message.charAt(0) - LETTER_VALUE) + (BOARD_SIZE - Integer.parseInt(message.substring(1))) * BOARD_SIZE);
    }
}
