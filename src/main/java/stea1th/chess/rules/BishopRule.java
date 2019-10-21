package stea1th.chess.rules;

import java.util.Arrays;
import java.util.HashSet;

import static stea1th.chess.enums.Direction.*;

public class BishopRule extends AbstractRule {

    public BishopRule() {
        super(new HashSet<>(Arrays.asList(NORDEAST, NORDWEST, SOUTHEAST, SOUTHWEST)));
    }

    @Override
    public void register() {
        addToRegisteredRules("B", this.getClass().getName());
    }

    @Override
    public void allPossibleMoves(int position) {
        allCellsTurn(position);
    }
}
