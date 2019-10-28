package stea1th.chess.rules.figures;

import stea1th.chess.figures.Figure;
import stea1th.chess.to.Move;

import java.util.Map;

public interface Rule {

    Map<Integer, Move> getAllPossibleMoves();

    void setFiguresInGame(Map<Integer, Figure> figuresInGame);

    void setMainFigure(Figure mainFigure);

//    boolean scanForPosition(int enemyKingPosition);
}
