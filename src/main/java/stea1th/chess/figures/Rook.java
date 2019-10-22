package stea1th.chess.figures;

public class Rook extends AbstractFigure {

    private Rook(String notation, String name, Integer position, boolean white) {
        super(notation, name, position, white);
    }

    public Rook() {
        super("R", "rook");
    }

    public Rook(Integer position, boolean white) {
        this("R", "rook", position, white);
    }
}
