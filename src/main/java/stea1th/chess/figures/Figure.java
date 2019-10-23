package stea1th.chess.figures;

import java.util.Map;

public interface Figure {

    String getNotation();

    String getName();

    Integer getPosition();

    String getView();

    boolean isActive();

    void setActive(boolean active);

    void register();

    void setPosition(Integer position);

    boolean move(int newPosition);

    void setFiguresInGame(Map<Integer, Figure> figuresInGame);

    boolean isWhite();

    boolean isAlive();

    void setAlive(boolean alive);

    int getMovesCount();


}
