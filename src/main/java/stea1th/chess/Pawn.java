package stea1th.chess;

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
