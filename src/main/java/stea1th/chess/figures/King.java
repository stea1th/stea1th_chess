package stea1th.chess.figures;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@EqualsAndHashCode(callSuper = true)
@ToString
public class King extends AbstractFigure {

    private boolean isAttacked = false;

    public King() {
        super("K", "king");
    }

    public King(Integer position, boolean white) {
        super("K", "king", position, white, true);
    }
}
