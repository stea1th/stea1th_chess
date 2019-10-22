package stea1th.chess.figures;

public class King extends AbstractFigure {

    private King(String notation, String name, Integer position, boolean white) {
        super(notation, name, position, white);
    }

    public King() {
        super("K", "king");
    }

    public King(Integer position, boolean white) {
        this("K", "king", position, white);
    }
}
