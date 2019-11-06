package stea1th.chess.rules.figures;

import stea1th.chess.rules.enums.Direction;
import stea1th.chess.to.Move;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

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
    public List<Move> findAllPossibleMoves() {
        List<Move> moves = new ArrayList<>(findPossibleMoves(mainFigure.getPosition()));
//        if (mainFigure.getMovesCount() == 0) {
//            moves.addAll(kingMovesForCastling(moves));
//        }
        return moves;
    }

    private List<Move> kingMovesForCastling(List<Move> allMoves) {
        Direction[] dirs = new Direction[]{EAST, WEST};
        List<Move> moves = new ArrayList<>();
        for (Direction dir : dirs) {
            Move move = allMoves.stream().filter(m -> m.getDirection() == dir).findFirst().get();
            getMovesForDirection(move.getNewPosition(), dir).forEach(m -> {
                m.setCastling(true);
                moves.add(m);
            });
        }
        return moves;
    }
}
