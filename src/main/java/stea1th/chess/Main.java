package stea1th.chess;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {

    private static final Scanner SCANNER = new Scanner(System.in);

    public static void main(String[] args) {
        Map<Integer, AbstractFigure> figures = new HashMap<>();
        AbstractFigure pawn = new Pawn(53, true);
        AbstractFigure pawn2 = new Pawn(54, true);
        AbstractFigure pawn3 = new Pawn(55, true);
        figures.put(pawn.getPosition(), pawn);
        figures.put(pawn2.getPosition(), pawn2);
        figures.put(pawn3.getPosition(), pawn3);
        playGame(figures);
    }

    public static void playGame(Map<Integer, AbstractFigure> figures) {
        while (true) {
            ChessBoard.printBoard(figures);
            System.out.println("your move: ");
            Integer[] positions = parsePositions(readFromConsole());
            Integer fromPosition = positions[0];
            AbstractFigure figure = figures.get(fromPosition);
            if(figure != null){
                figures.remove(fromPosition);
                figure.move(positions[1]);
                figures.put(figure.getPosition(), figure);
            }
        }
    }

    public static String readFromConsole() {
        return SCANNER.nextLine();
    }

    public static Integer[] parsePositions(String position) {
        return Arrays.stream(position.split(":"))
                .map(Integer::valueOf)
                .toArray(Integer[]::new);
    }
}
