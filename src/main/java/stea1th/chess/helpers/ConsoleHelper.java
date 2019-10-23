package stea1th.chess.helpers;

import stea1th.chess.figures.Figure;

import java.util.Arrays;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class ConsoleHelper {

    private ConsoleHelper() {
    }

    private static final Scanner SCANNER = new Scanner(System.in);
    private final static int BOARD_SIZE = 8;
    private static StringBuilder wDead = new StringBuilder();
    private static StringBuilder bDead = new StringBuilder();
    private static final String THREE_SPACES = "   ";
    private static final String TWO_SPACES = "  ";
    private static final String ONE_SPACE = " ";


    public static String readFromConsole() {
        System.out.print("your move: ");
        return SCANNER.nextLine();
    }

    public static Integer[] parsePositions(String position) {
        if (position == null) return null;
        return Arrays.stream(position.split(":"))
                .map(Integer::valueOf)
                .toArray(Integer[]::new);
    }

    public static void printBoard(Map<Integer, Figure> figures, boolean isWhite) {
        String[] deadPieces = getAllDeadPieces(figures);
        if (isWhite) {
            System.out.println("Killed white: " + deadPieces[0]);
            printForWhite(figures);
            System.out.println("Killed black: " + deadPieces[1]);
        } else {
            System.out.println("Killed black: " + deadPieces[1]);
            printForBlack(figures);
            System.out.println("Killed white: " + deadPieces[0]);
        }
    }

    private static void printForWhite(Map<Integer, Figure> figures) {

        for (int row = 0; row < BOARD_SIZE; row++) {
            System.out.println();
            System.out.println(THREE_SPACES + "---------------------------------");
            for (int column = 0; column <= BOARD_SIZE; column++) {
                if (column == 0) {
                    System.out.print(ONE_SPACE + (BOARD_SIZE - row) + ONE_SPACE);
                } else {
                    int num = column + BOARD_SIZE * row;
                    Figure figure = figures.get(num);
                    String cell = figure != null && figure.isAlive() ? ONE_SPACE + figure.getView() : getEmptyCell(row, num);
                    System.out.print("|" + cell);
                }
            }
            System.out.print("|");
        }
        System.out.println();
        System.out.println(THREE_SPACES + "---------------------------------");
        System.out.println(THREE_SPACES + createLettersChain());
        System.out.println();
    }

    private static String createLettersChain() {
        return IntStream.rangeClosed(65, 72)
                .mapToObj(i -> (char) i)
                .map(i -> Character.toString(i))
                .collect(Collectors.joining(THREE_SPACES, TWO_SPACES, TWO_SPACES));
    }

    private static String reverseLettersChain(String stringToReverse) {
        StringBuilder builder = new StringBuilder(stringToReverse.trim());
        return TWO_SPACES + builder.reverse().toString();
    }

    public static void printForBlack(Map<Integer, Figure> figures) {
        for (int row = BOARD_SIZE; row > 0; row--) {
            System.out.println();
            System.out.println(THREE_SPACES + "---------------------------------");
            for (int column = 0; column <= BOARD_SIZE; column++) {
                if (column == 0) {
                    System.out.print(ONE_SPACE + (BOARD_SIZE - row + 1) + ONE_SPACE);
                } else {
                    int num = BOARD_SIZE * row - column + 1;
                    Figure figure = figures.get(num);
                    String cell = figure != null && figure.isAlive() ? ONE_SPACE + figure.getView() : getEmptyCell(row, num);
                    System.out.print("|" + cell);
                }
            }
            System.out.print("|");
        }
        System.out.println();
        System.out.println(THREE_SPACES + "---------------------------------");
        System.out.println(THREE_SPACES + reverseLettersChain(createLettersChain()));
        System.out.println();
    }

    private static String[] getAllDeadPieces(Map<Integer, Figure> figures) {
        for (Figure figure : figures.values()) {
            if (!figure.isAlive()) {
                if (figure.isWhite())
                    wDead.append(wDead.length() == 0 ? figure.getView() : ", " + figure.getView());
                else
                    bDead.append(bDead.length() == 0 ? figure.getView() : ", " + figure.getView());
            }
        }
        return new String[]{wDead.toString(), bDead.toString()};
    }

    private static String getEmptyCell(int row, int num) {
        String white = THREE_SPACES;
        String black = "===";
        if (row % 2 == 0)
            return num % 2 == 1 ? white : black;
        else
            return num % 2 == 1 ? black : white;
    }

    public static void printForWhiteNumbers() {
        for (int row = 0; row < BOARD_SIZE; row++) {
            System.out.println();
            System.out.println("---------------------------------");
            for (int column = 1; column <= BOARD_SIZE; column++) {
                String num = "" + (column + BOARD_SIZE * row);
                System.out.print("| " + (num.length() == 1 ? num + ONE_SPACE : num));
            }
            System.out.print("|");
        }
        System.out.println();
        System.out.println("---------------------------------");
    }

    public static void printForBlackNumbers() {
        for (int row = BOARD_SIZE; row > 0; row--) {
            System.out.println();
            System.out.println("---------------------------------");
            for (int column = 0; column < BOARD_SIZE; column++) {
                String num = "" + (BOARD_SIZE * row - column);
                System.out.print("| " + (num.length() == 1 ? num + ONE_SPACE : num));
            }
            System.out.print("|");
        }
        System.out.println();
        System.out.println("---------------------------------");
    }
}
