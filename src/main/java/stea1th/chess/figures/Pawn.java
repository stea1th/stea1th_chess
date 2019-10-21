package stea1th.chess.figures;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Pawn extends AbstractFigure {

    public Pawn(String notation, String name, Integer position, boolean white) {
        super(notation, name, position, white);
    }

    public Pawn(Integer position, boolean white) {
        this("p","Pawn", position, white);
    }
}
