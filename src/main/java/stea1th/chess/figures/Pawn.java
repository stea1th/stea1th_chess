package stea1th.chess.figures;

import lombok.ToString;

@ToString
public class Pawn extends AbstractFigure {

    public Pawn() {
        super("p", "pawn");
    }

    public Pawn(Integer position, boolean white) {
        super("p", "pawn", position, white, true);
    }
}
