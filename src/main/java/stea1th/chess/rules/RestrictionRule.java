package stea1th.chess.rules;

import stea1th.chess.rules.enums.Direction;

public class RestrictionRule {

    private static final int BOARD_HEIGHT = 8;

    private RestrictionRule() {
    }

    public static boolean isRestricted(int position, Direction direction) {
        switch(direction) {
            case NORTH_WEST:
            case WEST:
            case SOUTH_WEST:
            case NORTH_LEFT:
            case SOUTH_LEFT:
                return checkWestRestriction(position);
            case NORTH_EAST:
            case EAST:
            case SOUTH_EAST:
            case NORTH_RIGHT:
            case SOUTH_RIGHT:
                return checkEastRestriction(position);
        }
        return false;
    }

    private static boolean checkEastRestriction(int position) {
        int factor = getFactor(position);
        return (factor + 1) * BOARD_HEIGHT == position;
    }

    private static boolean checkWestRestriction(int position) {
        int factor = getFactor(position);
        return factor * BOARD_HEIGHT + 1 == position;
    }

    private static int getFactor(int position) {
        return position / BOARD_HEIGHT;
    }

}
