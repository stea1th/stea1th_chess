package stea1th.chess;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Pawn extends AbstractFigure {

//    public Pawn(String name, String position, boolean white, boolean alive) {
//        super(name, position, white, alive);
//    }

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
