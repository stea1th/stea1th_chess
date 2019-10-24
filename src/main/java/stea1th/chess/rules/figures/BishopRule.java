package stea1th.chess.rules.figures;

import lombok.ToString;
import stea1th.chess.figures.Figure;

import java.util.Arrays;
import java.util.HashSet;

import static stea1th.chess.rules.enums.Direction.*;

@ToString
public class BishopRule extends AbstractRule {

    public BishopRule() {
        super(new HashSet<>(Arrays.asList(NORTH_EAST, NORTH_WEST, SOUTH_EAST, SOUTH_WEST)));
    }

    @Override
    public void register() {
        addToRegisteredRules("B", this.getClass().getName());
    }

    @Override
    public void findAllPossibleMoves(Figure figure) {
        moreCellsTurn(figure.getPosition());
    }

    @Override
    public boolean scanForPosition(int enemyKingPosition) {
        return scanMoreCellsTurn(enemyKingPosition);
    }

}
