package stea1th.chess.pieces;

import java.util.Map;

public interface Piece {

    String getNotation();

    String getName();

    Integer getPosition();

    void setPosition(Integer position);

    boolean move(int newPosition);

    void register();

    void setFiguresInGame(Map<Integer, Piece> figuresInGame);

    boolean isWhite();


}
