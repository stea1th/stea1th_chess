package stea1th.chess;

import java.util.HashMap;
import java.util.Map;

import static stea1th.chess.ConsoleHelper.parsePositions;
import static stea1th.chess.ConsoleHelper.readFromConsole;

public class Game {

    private Map<Integer, Figure> figures = new HashMap<>();

    public void init() {
        Figure pawn = new Pawn(53, true);
        Figure pawn2 = new Pawn(54, true);
        Figure pawn3 = new Pawn(55, true);
        figures.put(pawn.getPosition(), pawn);
        figures.put(pawn2.getPosition(), pawn2);
        figures.put(pawn3.getPosition(), pawn3);
    }

    public void play() {
        while (true) {
            ChessBoard.printBoard(figures);
            Integer[] positions = parsePositions(readFromConsole());
            Integer fromPosition = positions[0];
            Figure figure = figures.get(fromPosition);
            if(figure != null){
                figures.remove(fromPosition);
                figure.move(positions[1]);
                figures.put(figure.getPosition(), figure);
            }
        }
    }

}
