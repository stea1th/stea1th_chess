package stea1th.chess.pieces;

public class Bishop extends AbstractPiece {

    private Bishop(String notation, String name, Integer position, boolean white) {
        super(notation, name, position, white);
    }

    public Bishop(Integer position, boolean white) {
        this("B", "Bishop", position, white);
    }
}
