package stea1th.chess.rules.figures;

import lombok.ToString;
import stea1th.chess.pieces.Piece;
import stea1th.chess.rules.enums.Direction;

import java.util.HashSet;

import static stea1th.chess.rules.enums.Direction.NORD;
import static stea1th.chess.rules.enums.Direction.SOUTH;

@ToString
public class PawnFigureRule extends AbstractFigureRule {

    public PawnFigureRule() {
        super(new HashSet<>());
    }

    @Override
    public void register() {
        addToRegisteredRules("p", this.getClass().getName());
    }

    @Override
    public void allPossibleMoves(Piece piece) {
        Direction dir = piece.isWhite()? NORD : SOUTH;
        addToDirections(dir);
        oneCellTurn(piece.getPosition());
    }
}
