package stea1th.chess.rules.figures;

import java.util.Arrays;
import java.util.HashSet;

import static stea1th.chess.rules.enums.Direction.*;

public class QueenRule extends AbstractRule {

    public QueenRule() {
        super(new HashSet<>(Arrays.asList(NORTH, SOUTH, WEST, EAST, NORTH_EAST, NORTH_WEST, SOUTH_EAST, SOUTH_WEST)));
    }

    @Override
    public void register() {
        addToRegisteredRules("Q", this.getClass().getName());
    }
}
