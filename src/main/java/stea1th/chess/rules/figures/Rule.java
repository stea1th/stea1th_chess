package stea1th.chess.rules.figures;

import stea1th.chess.figures.Figure;

import java.util.Map;

public interface Rule {

    Map<String, Integer> getAllPossibleMoves(Figure figure);

    void setFiguresInGame(Map<Integer, Figure> figuresInGame);

    void setMainFigure(Figure mainFigure);
}
