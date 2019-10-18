package stea1th.chess;


import lombok.Data;

@Data
public abstract class AbstractFigure {

    private String name;
    private String position;
    private boolean white;
    private boolean alive;

    public abstract void move(String newPosition);
}
