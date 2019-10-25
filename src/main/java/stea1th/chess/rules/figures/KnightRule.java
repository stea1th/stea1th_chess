package stea1th.chess.rules.figures;

import lombok.ToString;
import stea1th.chess.figures.Figure;

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

//    @Override
//    public boolean scanForPosition(int enemyKingPosition) {
//        return false;
//    }
}
