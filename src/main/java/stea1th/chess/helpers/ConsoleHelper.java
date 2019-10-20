package stea1th.chess.helpers;

import java.util.Arrays;
import java.util.Scanner;

public class ConsoleHelper {

    private static final Scanner SCANNER = new Scanner(System.in);

    public static String readFromConsole() {
        System.out.println("your move: ");
        return SCANNER.nextLine();
    }

    public static Integer[] parsePositions(String position) {
        return Arrays.stream(position.split(":"))
                .map(Integer::valueOf)
                .toArray(Integer[]::new);
    }
}
