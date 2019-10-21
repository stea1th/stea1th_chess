package stea1th.chess.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public enum Direction {

    MIN(0),
    MAX(65),
    NORD(-8),
    WEST(-1),
    EAST(1),
    SOUTH(8),
    NORDWEST(-9),
    NORDEAST(-7),
    SOUTHWEST(7),
    SOUTHEAST(9);

    @Getter
    public final int value;


}
