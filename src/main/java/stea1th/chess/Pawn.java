package stea1th.chess;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Pawn extends AbstractFigure {

    public Pawn(String name, Integer position, boolean white, boolean alive) {
        super(name, position, white, alive);
    }

    public Pawn(Integer position, boolean white) {
        this("P", position, white, true);
    }

    @Override
    public void register() {

    }

    @Override
    public boolean move(int position) {
        boolean isValid = isTurnValid(position);
        if (isValid)
            this.setPosition(position);
        return isValid;
    }

    @Override
    public boolean isTurnValid(int newPosition) {
        return rule() == newPosition;
    }

    private int rule() {
        return this.getPosition() - 8;
    }
}
