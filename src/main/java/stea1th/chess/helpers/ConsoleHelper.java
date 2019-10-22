package stea1th.chess.helpers;

import stea1th.chess.figures.Figure;

import java.util.Arrays;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

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

    private final static int BOARD_SIZE = 8;


    public static void printBoard(Map<Integer, Figure> figures) {
        String[] deadPieces = getAllDeadPieces(figures);
        System.out.println("Killed white: " + deadPieces[0]);
        printForWhite(figures);
        System.out.println("Killed black: " + deadPieces[1]);
    }

    public static void printForWhite(Map<Integer, Figure> figures) {
        String emptyString = "   ";
        for (int row = 0; row < BOARD_SIZE; row++) {
            System.out.println();
            System.out.println(emptyString + "---------------------------------");
            for (int column = 0; column <= BOARD_SIZE; column++) {
                if (column == 0) {
                    System.out.print(" " + (BOARD_SIZE - row) + " ");
                } else {
                    int num = column + BOARD_SIZE * row;
                    Figure figure = figures.get(num);
                    String cell = figure != null && figure.isAlive() ? " " + figure.getName() : getEmptyCell(row, num);
                    System.out.print("|" + cell);
                }
            }
            System.out.print("|");
        }
        System.out.println();
        System.out.println(emptyString + "---------------------------------");

        System.out.println(emptyString + IntStream.rangeClosed(65, 72)
                .mapToObj(i -> (char) i)
                .map(i -> Character.toString(i))
                .collect(Collectors.joining(emptyString, "  ", "  ")));

    }

    public static void printForBlack(Map<Integer, Figure> figures) {
        for (int row = BOARD_SIZE; row > 0; row--) {
            System.out.println();
            System.out.println("---------------------------------");
            for (int column = 0; column < BOARD_SIZE; column++) {
                int num = BOARD_SIZE * row - column;
                Figure figure = figures.get(num);
                String cell = figure != null && figure.isAlive() ? " " + figure.getName() : getEmptyCell(row, num);
                System.out.print("|" + cell);
            }
            System.out.print("|");
        }
        System.out.println();
        System.out.println("---------------------------------");
    }

    private static String[] getAllDeadPieces(Map<Integer, Figure> figures) {
        StringBuilder wDead = new StringBuilder();
        StringBuilder bDead = new StringBuilder();

        for (Figure figure : figures.values()) {
            if (!figure.isAlive()) {
                if (figure.isWhite())
                    wDead.append(wDead.length() == 0 ? figure.getName() : ", " + figure.getName());
                else
                    bDead.append(bDead.length() == 0 ? figure.getName() : ", " + figure.getName());
            }
        }
        return new String[]{wDead.toString(), bDead.toString()};
    }

    public static String getEmptyCell(int row, int num) {
        String white = "   ";
        String black = "===";
        if (row % 2 == 0) {
            return num % 2 == 1 ? white : black;
        } else {
            return num % 2 == 1 ? black : white;
        }
    }

    public static void printForWhiteNumbers() {
        for (int row = 0; row < BOARD_SIZE; row++) {
            System.out.println();
            System.out.println("---------------------------------");
            for (int column = 1; column <= BOARD_SIZE; column++) {
                String num = "" + (column + BOARD_SIZE * row);
                System.out.print("| " + (num.length() == 1 ? num + " " : num));
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
                System.out.print("| " + (num.length() == 1 ? num + " " : num));
            }
            System.out.print("|");
        }
        System.out.println();
        System.out.println("---------------------------------");
    }
}
