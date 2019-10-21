package stea1th.chess.figures;

public interface Figure {

    String getNotation();

    String getName();

    Integer getPosition();

    void setPosition(Integer position);

    boolean move(int newPosition);

    void register();
}
