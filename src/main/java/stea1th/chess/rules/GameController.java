package stea1th.chess.rules;

import lombok.Getter;
import stea1th.chess.figures.Bishop;
import stea1th.chess.figures.Knight;
import stea1th.chess.figures.Pawn;
import stea1th.chess.figures.Figure;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

public class GameController {

    @Getter
    private Map<Integer, Figure> figuresInGame = new HashMap<>();

    private static final AtomicInteger count = new AtomicInteger(100);

    public GameController() {
        init();
    }

    public void init() {
        Figure pawn = new Pawn(53, true);
        Figure pawn2 = new Pawn(54, true);
        Figure pawn3 = new Pawn(45, false);
        Figure bishop = new Bishop(59, true);
        Figure knight = new Knight(58, true);
        figuresInGame.put(pawn.getPosition(), pawn);
        figuresInGame.put(pawn2.getPosition(), pawn2);
        figuresInGame.put(pawn3.getPosition(), pawn3);
        figuresInGame.put(bishop.getPosition(), bishop);
        figuresInGame.put(knight.getPosition(), knight);
    }

    public boolean moveFigure(Integer[] positions) {
        Integer fromPosition = positions[0];
        Figure figure = figuresInGame.get(fromPosition);
        if(figure != null){
            figure.setFiguresInGame(figuresInGame);
            figuresInGame.remove(fromPosition);
            if(figure.move(positions[1])) {
                killEnemy(figure);
            }
            figuresInGame.put(figure.getPosition(), figure);
            return true;
        }
        return false;
    }

    private void killEnemy(Figure figure) {
        int newPosition = figure.getPosition();
        Figure anotherFigure = figuresInGame.get(newPosition);
        if(anotherFigure != null && anotherFigure.isWhite() != figure.isWhite()) {
            figuresInGame.remove(newPosition);
            anotherFigure.setAlive(false);
            figuresInGame.put(count.get(), anotherFigure);
        }
    }
}
