package stea1th.chess;

import lombok.Getter;
import stea1th.chess.figures.Figure;
import stea1th.chess.figures.Pawn;

import java.util.HashMap;
import java.util.Map;

import static stea1th.chess.helpers.ConsoleHelper.parsePositions;
import static stea1th.chess.helpers.ConsoleHelper.readFromConsole;

public class Game {

    @Getter
    private Map<Integer, Figure> figuresInGame = new HashMap<>();

    public void init() {
        Figure pawn = new Pawn(53, true);
        Figure pawn2 = new Pawn(54, true);
        Figure pawn3 = new Pawn(55, true);
        figuresInGame.put(pawn.getPosition(), pawn);
        figuresInGame.put(pawn2.getPosition(), pawn2);
        figuresInGame.put(pawn3.getPosition(), pawn3);
    }

    public void play() {
        while (true) {
            ChessBoard.printBoard(figuresInGame);
            Integer[] positions = parsePositions(readFromConsole());
            Integer fromPosition = positions[0];
            Figure figure = figuresInGame.get(fromPosition);
            if(figure != null){
                figuresInGame.remove(fromPosition);
                figure.move(positions[1]);
                figuresInGame.put(figure.getPosition(), figure);
            }
        }
    }

}
