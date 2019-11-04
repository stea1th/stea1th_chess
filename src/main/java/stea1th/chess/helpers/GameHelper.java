package stea1th.chess.helpers;

import stea1th.chess.figures.Figure;

public class GameHelper {

    private GameHelper() {
    }

    public static <T> boolean exist(T object) {
        return object != null;
    }

    public static <T extends Figure> boolean isSameColor(T first, T second) {
        return isSameColor(first.isWhite(), second.isWhite());
    }

    public static <T extends Figure> boolean isSameColor(T first, boolean isWhite) {
        return isSameColor(first.isWhite(), isWhite);
    }

    public static <T extends Figure> boolean isSameColor(boolean isFirstWhite, boolean isSecondWhite) {
        return isFirstWhite == isSecondWhite;
    }

}
