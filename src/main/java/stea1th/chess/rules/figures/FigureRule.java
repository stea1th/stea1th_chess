package stea1th.chess.rules.figures;

import stea1th.chess.pieces.Piece;

import java.util.Map;

public interface FigureRule {

    Map<String, Integer> getAllPossibleMoves(int position);

    void setFiguresInGame(Map<Integer, Piece> figuresInGame);

    void setPiece(Piece piece);
}
