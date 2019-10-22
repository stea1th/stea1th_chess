package stea1th.chess.figures;

import lombok.ToString;

@ToString
public class Bishop extends AbstractFigure {

    private Bishop(String notation, String name, Integer position, boolean white) {
        super(notation, name, position, white);
    }

    public Bishop() {
        super("B", "bishop");
    }

    public Bishop(Integer position, boolean white) {
        this("B", "bishop", position, white);
    }
}
