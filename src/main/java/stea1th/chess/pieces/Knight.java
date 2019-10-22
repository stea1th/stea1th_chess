package stea1th.chess.pieces;

public class Knight extends AbstractPiece {

    public Knight(String notation, String name, Integer position, boolean white) {
        super(notation, name, position, white);
    }

    public Knight(Integer position, boolean white) {
        this("N", "Knight", position, white);
    }
}
