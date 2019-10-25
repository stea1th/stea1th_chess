package stea1th.chess.figures;

import lombok.ToString;

@ToString
public class Knight extends AbstractFigure {

    public Knight() {
        super("N", "knight");
    }

    public Knight(Integer position, boolean white) {
        super("N", "knight", position, white, true);
    }
}
