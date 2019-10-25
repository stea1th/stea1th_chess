package stea1th.chess.figures;

import lombok.ToString;

@ToString
public class Bishop extends AbstractFigure {

    public Bishop() {
        super("B", "bishop");
    }

    public Bishop(Integer position, boolean white) {
        super("B", "bishop", position, white, false);
    }
}
