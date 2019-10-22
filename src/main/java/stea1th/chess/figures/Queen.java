package stea1th.chess.figures;

public class Queen extends AbstractFigure {

    private Queen(String notation, String name, Integer position, boolean white) {
        super(notation, name, position, white);
    }

    public Queen() {
        super("Q", "queen");
    }

    public Queen(Integer position, boolean white) {
        this("Q", "queen", position, white);
    }
}
