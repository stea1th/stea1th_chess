package stea1th.chess.rules.figures;

import lombok.ToString;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;

import static stea1th.chess.rules.enums.Direction.NORD;
import static stea1th.chess.rules.enums.Direction.SOUTH;

@ToString
public class PawnFigureRule extends AbstractFigureRule {

    public PawnFigureRule() {
        super(new HashSet<>(Collections.singletonList(NORD)));
    }

    @Override
    public void register() {
        addToRegisteredRules("p", this.getClass().getName());
    }

    @Override
    public void allPossibleMoves(int position) {
        oneCellTurn(position);
    }
}
