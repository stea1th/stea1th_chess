package stea1th.chess.figures;

import stea1th.chess.rules.figures.Rule;

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

    boolean move(int newPosition);

    void setFiguresInGame(Map<Integer, Figure> figuresInGame);

    boolean isWhite();

    boolean isAlive();

    void setAlive(boolean alive);

    boolean isOneTurn();

    int getMovesCount();

    void setKingChecked(boolean checked);

    boolean isKingChecked();


}
