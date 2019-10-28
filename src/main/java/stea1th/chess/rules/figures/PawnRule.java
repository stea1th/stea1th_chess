package stea1th.chess.rules.figures;

import lombok.ToString;
import stea1th.chess.rules.enums.Direction;
import stea1th.chess.to.Move;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

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
    public List<Move> findAllPossibleMoves() {
        addToDirections(mainFigure.isWhite() ? NORTH : SOUTH);
        List<Move> moves = new ArrayList<>(findPossibleMoves(mainFigure.getPosition()));
        if (mainFigure.getMovesCount() == 0 && !moves.isEmpty()) {
            moves.addAll(findPossibleMoves(moves.get(0).getNewPosition(), false));
        }
        moves.addAll(pawnMovesIfEnemyNearby());
        return moves;
    }

    private List<Move> pawnMovesIfEnemyNearby() {
        Direction[] dirs = mainFigure.isWhite() ? new Direction[]{NORTH_EAST, NORTH_WEST} : new Direction[]{SOUTH_EAST, SOUTH_WEST};
        return getMovesForDirections(mainFigure.getPosition(), new HashSet<>(Arrays.asList(dirs)))
                .stream()
                .filter(i -> isEnemyNearby(i.getNewPosition()))
                .collect(Collectors.toList());
    }
}
