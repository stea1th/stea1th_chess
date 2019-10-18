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
    public void move(int position) {
        this.setPosition(position);
    }

    @Override
    public boolean isTurnValid(String newPosition) {
        return true;

    }
}
