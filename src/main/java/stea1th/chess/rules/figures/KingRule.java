package stea1th.chess.rules.figures;

import stea1th.chess.figures.Figure;

import java.util.Arrays;
import java.util.HashSet;

import static stea1th.chess.rules.enums.Direction.*;

public class KingRule extends AbstractRule {

    public KingRule() {
        super(new HashSet<>(Arrays.asList(NORTH, SOUTH, WEST, EAST, NORTH_EAST, NORTH_WEST, SOUTH_EAST, SOUTH_WEST)));
    }

    @Override
    public void register() {
        addToRegisteredRules("K", this.getClass().getName());
    }

    @Override
    public void findAllPossibleMoves(Figure figure) {
        oneCellTurn(figure.getPosition());
    }

    @Override
    public boolean scanForPosition(int enemyKingPosition) {
        return false;
    }
}
