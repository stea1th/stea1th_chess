package stea1th.chess.figures;

import lombok.ToString;

@ToString
public class Pawn extends AbstractFigure {

    private Pawn(String notation, String name, Integer position, boolean white) {
        super(notation, name, position, white);
    }

    public Pawn() {
        super("p", "pawn");
    }

    public Pawn(Integer position, boolean white) {
        this("p", "pawn", position, white);
    }
}
