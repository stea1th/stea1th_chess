package stea1th.chess.rules.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public enum Direction {

    MIN(0),
    MAX(65),
    NORTH(-8),
    NORTH_WEST(-9),
    NORTH_EAST(-7),
    NORTH_LEFT(-17),
    NORTH_RIGHT(-15),
    SOUTH(8),
    SOUTH_WEST(7),
    SOUTH_EAST(9),
    SOUTH_LEFT(15),
    SOUTH_RIGHT(17),
    WEST(-1),
    WEST_UP(-10),
    WEST_DOWN(6),
    EAST(1),
    EAST_UP(-6),
    EAST_DOWN(10);

    @Getter
    public final int value;


}
