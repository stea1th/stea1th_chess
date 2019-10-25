package stea1th.chess.figures;

import lombok.ToString;

@ToString
public class Queen extends AbstractFigure {

    public Queen() {
        super("Q", "queen");
    }

    public Queen(Integer position, boolean white) {
        super("Q", "queen", position, white, false);
    }
}
