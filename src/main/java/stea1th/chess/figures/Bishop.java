package stea1th.chess.figures;

public class Bishop extends AbstractFigure {

    private Bishop(String notation, String name, Integer position, boolean white) {
        super(notation, name, position, white);
    }

    public Bishop(Integer position, boolean white) {
        this("B", "Bishop", position, white);
    }
}
