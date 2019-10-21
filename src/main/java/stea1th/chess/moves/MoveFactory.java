package stea1th.chess.moves;

import stea1th.chess.enums.Direction;

public class MoveFactory {

    public static int getAdjoiningPosition(int position, Direction direction) {
        return position + direction.value;
    }
}
