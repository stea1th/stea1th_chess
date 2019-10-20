package stea1th.chess;

public interface Figure {

    String getName();

    Integer getPosition();

    void setPosition(Integer position);

    boolean move(int newPosition);
}
