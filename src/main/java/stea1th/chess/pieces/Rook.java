package stea1th.chess.pieces;

public class Rook extends AbstractPiece {

    public Rook(String notation, String name, Integer position, boolean white) {
        super(notation, name, position, white);
    }

    public Rook(Integer position, boolean white) {
        this("R", "Rook", position, white);
    }
}
