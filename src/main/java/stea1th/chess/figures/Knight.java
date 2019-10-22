package stea1th.chess.figures;

import lombok.ToString;

@ToString
public class Knight extends AbstractFigure {

    private Knight(String notation, String name, Integer position, boolean white) {
        super(notation, name, position, white);
    }

    public Knight() {
        super("N", "knight");
    }

    public Knight(Integer position, boolean white) {
        this("N", "knight", position, white);
    }
}
