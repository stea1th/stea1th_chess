package stea1th.chess.figures;

public class Rook extends AbstractFigure {

    public Rook(String notation, String name, Integer position, boolean white) {
        super(notation, name, position, white);
    }

    public Rook(Integer position, boolean white) {
        this("R", "Rook", position, white);
    }

}
