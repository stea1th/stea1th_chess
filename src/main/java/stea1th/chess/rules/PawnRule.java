package stea1th.chess.rules;

import java.util.Arrays;
import java.util.HashSet;

import static stea1th.chess.enums.Direction.NORD;
import static stea1th.chess.enums.Direction.SOUTH;

public class PawnRule extends AbstractRule {

    public PawnRule() {
        super(new HashSet<>(Arrays.asList(NORD, SOUTH)));
    }

    @Override
    public void register() {
        addToRegisteredRules("p", this.getClass().getName());
    }

    @Override
    public void allPossibleMoves(int position) {
        oneCellTurn(position);
    }
}
