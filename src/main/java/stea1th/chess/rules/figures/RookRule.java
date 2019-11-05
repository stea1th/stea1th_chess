package stea1th.chess.rules.figures;

import java.util.Arrays;
import java.util.HashSet;

import static stea1th.chess.rules.enums.Direction.*;

public class RookRule extends AbstractRule {

    public RookRule() {
        super(new HashSet<>(Arrays.asList(NORTH, SOUTH, EAST, WEST)));
    }

    @Override
    public void register() {
        addToRegisteredRules("R", this.getClass().getName());
    }
}
