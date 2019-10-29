package stea1th.chess.rules.figures;

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

//    @Override
//    public List<Move> findAllPossibleMoves() {
//        King king = (King) mainFigure;
//        if(!king.isAttacked()) {
//            return super.findAllPossibleMoves();
//        } else {
//
//        }
//    }

}
