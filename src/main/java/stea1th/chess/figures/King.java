package stea1th.chess.figures;

import lombok.ToString;

@ToString
public class King extends AbstractFigure {

    public King() {
        super("K", "king");
    }

    public King(Integer position, boolean white) {
        super("K", "king", position, white, true);
    }
}
