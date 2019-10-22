package stea1th.chess.rules.figures;

import lombok.ToString;
import stea1th.chess.pieces.Piece;
import stea1th.chess.rules.enums.Direction;

import java.util.HashSet;

import static stea1th.chess.rules.enums.Direction.*;

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
        Direction dir = piece.isWhite() ? NORD : SOUTH;
        addToDirections(dir);
        oneCellTurn(piece.getPosition());
        pawnMovesIfEnemyNearby(piece);
    }

    private void pawnMovesIfEnemyNearby(Piece piece) {
        Direction[] dirs = piece.isWhite() ? new Direction[]{NORDEAST, NORDWEST} : new Direction[]{SOUTHEAST, SOUTHWEST};
        for (Direction dir: dirs) {
            Integer position = getAdjoiningPosition(piece.getPosition(), dir);
            if(isEnemyNearby(position))
                addToPossibleMoves(position);
        }
    }
}
