package stea1th.chess.figures;

import lombok.ToString;

@ToString
public class Rook extends AbstractFigure {

    public Rook() {
        super("R", "rook");
    }

    public Rook(Integer position, boolean white) {
        super("R", "rook", position, white, false);
    }
}
