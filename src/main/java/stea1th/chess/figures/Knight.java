package stea1th.chess.figures;

public class Knight extends AbstractFigure {

    public Knight(String notation, String name, Integer position, boolean white) {
        super(notation, name, position, white);
    }

    public Knight(Integer position, boolean white) {
        this("N", "Knight", position, white);
    }
}
