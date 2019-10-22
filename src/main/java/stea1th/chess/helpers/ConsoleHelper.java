package stea1th.chess.helpers;

import stea1th.chess.pieces.Piece;

import java.util.Arrays;
import java.util.Map;
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

    private final static int BOARD_SIZE = 8;


    public static void printBoard(Map<Integer, Piece> figures) {
        String[] deadPieces = getAllDeadPieces(figures);
        System.out.println("Killed white: " + deadPieces[0]);
        for (int row = 0; row < BOARD_SIZE; row++) {
            System.out.println();
            System.out.println("---------------------------------");
            for (int column = 1; column <= BOARD_SIZE; column++) {
                int num = column + BOARD_SIZE * row;
                Piece piece = figures.get(num);
                String cell = piece != null && piece.isAlive() ? " " + piece.getName() : getEmptyCell(row, num);
                System.out.print("|" + cell);
            }
            System.out.print("|");
        }
        System.out.println();
        System.out.println("---------------------------------");

        System.out.println("Killed black: " + deadPieces[1]);
    }

    private static String[] getAllDeadPieces(Map<Integer, Piece> figures) {
        StringBuilder wDead = new StringBuilder();
        StringBuilder bDead = new StringBuilder();

        for (Piece piece : figures.values()) {
            if (!piece.isAlive()) {
                if (piece.isWhite())
                    wDead.append(piece.getName()).append(", ");
                else
                    bDead.append(piece.getName()).append(", ");
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

    public static void printNumbers() {
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
}
