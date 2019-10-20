package stea1th.chess.rules;

import stea1th.chess.moves.Move;
import stea1th.chess.moves.NordMove;

public class PawnRule extends AbstractRule {

    @Override
    public void register() {
        addToRegisteredRules("p", this.getClass().getName());
    }

    @Override
    public void allPossibleMoves(int position) {
        Move move = new NordMove();
        addToPossibleMoves(move.getAdjoiningPosition(position));
    }


}
