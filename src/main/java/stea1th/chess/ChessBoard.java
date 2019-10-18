package stea1th.chess;

import java.util.Map;

public class ChessBoard {

    private final static int BOARD_SIZE = 8;


    public static void printBoard(Map<Integer, AbstractFigure> figures) {

        for (int row = 0; row < BOARD_SIZE; row++) {
            System.out.println("");
            System.out.println("---------------------------------");
            for (int column = 1; column <= BOARD_SIZE; column++) {
                int num = column + BOARD_SIZE * row;
                AbstractFigure figure = figures.get(num);
                String cell = figure != null ? " " + figure.getName() + " " : getEmptyCell(row, num);
                System.out.print("|" + cell);
            }
            System.out.print("|");
        }
        System.out.println("");
        System.out.println("---------------------------------");
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
            System.out.println("");
            System.out.println("---------------------------------");
            for (int column = 1; column <= BOARD_SIZE; column++) {
                String num = "" + (column + BOARD_SIZE * row);
                System.out.print("| " + (num.length() == 1 ? num + " " : num));
            }
            System.out.print("|");
        }
        System.out.println("");
        System.out.println("---------------------------------");
    }
}
