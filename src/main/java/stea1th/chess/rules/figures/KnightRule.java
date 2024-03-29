package stea1th.chess.rules.figures;

import lombok.ToString;

import java.util.Arrays;
import java.util.HashSet;

import static stea1th.chess.rules.enums.Direction.*;

@ToString
public class KnightRule extends AbstractRule {

    public KnightRule() {
        super(new HashSet<>(Arrays.asList(NORTH_LEFT, NORTH_RIGHT, SOUTH_LEFT, SOUTH_RIGHT, WEST_UP, WEST_DOWN, EAST_UP, EAST_DOWN)));
    }

    @Override
    public void register() {
        addToRegisteredRules("N", this.getClass().getName());
    }
}
