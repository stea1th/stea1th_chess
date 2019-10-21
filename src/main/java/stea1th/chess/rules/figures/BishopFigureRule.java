package stea1th.chess.rules.figures;

import lombok.ToString;
import stea1th.chess.pieces.Piece;

import java.util.Arrays;
import java.util.HashSet;

import static stea1th.chess.rules.enums.Direction.*;

@ToString
public class BishopFigureRule extends AbstractFigureRule {

    public BishopFigureRule() {
        super(new HashSet<>(Arrays.asList(NORDEAST, NORDWEST, SOUTHEAST, SOUTHWEST)));
    }

    @Override
    public void register() {
        addToRegisteredRules("B", this.getClass().getName());
    }

    @Override
    public void allPossibleMoves(Piece piece) {
        allCellsTurn(piece.getPosition());
    }

}
