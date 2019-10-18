package stea1th.chess;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
//        ChessBoard.printBoard();
        Map<Integer, AbstractFigure> figures = new HashMap<>();
//        ChessBoard.printNumbers();
        AbstractFigure pawn = new Pawn();
        pawn.setName("P");
        pawn.setPosition(53);
        figures.put(pawn.getPosition(), pawn);
        playGame(figures);
    }

    public static void playGame(Map<Integer, AbstractFigure> figures) {
        Scanner scanner = new Scanner(System.in);
        int pos = 53;
        while (true) {
            ChessBoard.printBoard(figures);
            System.out.println("your move: ");
            AbstractFigure figure = figures.get(pos);
            figures.clear();
            pos = scanner.nextInt();
            figure.move(pos);
            figures.put(figure.getPosition(), figure);
        }
    }
}
