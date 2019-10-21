package stea1th.chess.moves;

import stea1th.chess.enums.Direction;

import static stea1th.chess.rules.AbstractRule.isInBorders;

public class MoveFactory {

    public static Integer getAdjoiningPosition(int position, Direction direction) {
        int result = position + direction.value;
        return isInBorders(result)? result : null;
    }
}
