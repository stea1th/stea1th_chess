package stea1th.chess.figures;

import stea1th.chess.rules.figures.Rule;
import stea1th.chess.to.Move;

import java.util.Map;

public interface Figure {

    String getNotation();

    String getName();

    Integer getPosition();

    String getView();

    Rule getRule();

    boolean isActive();

    void setActive(boolean active);

    void register();

    void setPosition(Integer position);

    void setFiguresInGame(Map<Integer, Figure> figuresInGame);

    boolean isWhite();

    boolean isAlive();

    void setAlive(boolean alive);

    boolean isOneTurn();

    int getMovesCount();

    void incrementMove();

    Move getMove(int to);

    void setAllPossibleMoves(Map<Integer, Move> moves);
}
