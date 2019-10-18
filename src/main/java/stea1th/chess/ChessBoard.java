package stea1th.chess;

public class ChessBoard {

    private final static int BOARD_SIZE = 8;


    public static void printBoard() {
        String white = "   ";
        String black = "===";
        for (int row = 0; row < BOARD_SIZE; row++) {
            System.out.println("");
            System.out.println("---------------------------------");
            for (int column = 1; column <= BOARD_SIZE; column++) {
                int num = column + BOARD_SIZE * row;
                if (row % 2 == 0) {
                    System.out.print("|" + (num % 2 == 1 ? white : black));
                } else {
                    System.out.print("|" + (num % 2 == 1 ? black : white));
                }
            }
            System.out.print("|");
        }
        System.out.println("");
        System.out.println("---------------------------------");
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
