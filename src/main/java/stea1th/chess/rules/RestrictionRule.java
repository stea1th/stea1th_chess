package stea1th.chess.rules;

import stea1th.chess.rules.enums.Direction;

public class RestrictionRule {

    private static final int BOARD_HEIGHT = 8;

    private RestrictionRule() {
    }

    public static boolean isRestricted(int position, Direction direction) {
        switch (direction) {
            case NORTH_WEST:
            case WEST:
            case SOUTH_WEST:
            case NORTH_LEFT:
            case SOUTH_LEFT:
                return checkWestRestrictions(position, 1);
            case NORTH_EAST:
            case EAST:
            case SOUTH_EAST:
            case NORTH_RIGHT:
            case SOUTH_RIGHT:
                return checkEastRestrictions(position, 1);
            case WEST_UP:
            case WEST_DOWN:
                return checkWestRestrictions(position, 2);
            case EAST_UP:
            case EAST_DOWN:
                return checkEastRestrictions(position, 2);
            default:
                return false;
        }
    }

    private static boolean checkEastRestrictions(int position, int repetition) {
        for (int i = 0; i < repetition; i++) {
            if (checkEastRestriction(position, i)) return true;
        }
        return false;
    }

    private static boolean checkEastRestriction(int position, int subtraction) {
        int factor = getFactor(position);
        return (factor + 1) * BOARD_HEIGHT - subtraction == position;
    }

    private static boolean checkWestRestrictions(int position, int repetition) {
        for (int i = 1; i <= repetition; i++) {
            if (checkWestRestriction(position, i)) return true;
        }
        return false;
    }

    private static boolean checkWestRestriction(int position, int addition) {
        int factor = getFactor(position);
        return factor * BOARD_HEIGHT + addition == position;
    }

    private static int getFactor(int position) {
        return position / BOARD_HEIGHT;
    }

}
