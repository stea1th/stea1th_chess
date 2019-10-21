package stea1th.chess.rules.figures;

import stea1th.chess.pieces.Piece;

import java.util.Map;

public interface FigureRule {

    Map<String, Integer> getAllPossibleMoves(Piece piece);

    void setFiguresInGame(Map<Integer, Piece> figuresInGame);

    void setMainPiece(Piece mainPiece);
}
