package stea1th.chess.rules.figures;

import lombok.ToString;
import stea1th.chess.figures.Figure;
import stea1th.chess.rules.enums.Direction;

import java.util.HashSet;

import static stea1th.chess.rules.enums.Direction.*;

@ToString
public class PawnRule extends AbstractRule {

    public PawnRule() {
        super(new HashSet<>());
    }

    @Override
    public void register() {
        addToRegisteredRules("p", this.getClass().getName());
    }

    @Override
    public void allPossibleMoves(Figure figure) {
        Direction dir = figure.isWhite() ? NORTH : SOUTH;
        addToDirections(dir);
        oneCellTurn(figure.getPosition());
        pawnMovesIfEnemyNearby(figure);
    }

    private void pawnMovesIfEnemyNearby(Figure figure) {
        Direction[] dirs = figure.isWhite() ? new Direction[]{NORTH_EAST, NORTH_WEST} : new Direction[]{SOUTH_EAST, SOUTH_WEST};
        for (Direction dir : dirs) {
            Integer position = getAdjoiningPosition(figure.getPosition(), dir);
            if (isEnemyNearby(position))
                addToPossibleMoves(position);
        }
    }
}
