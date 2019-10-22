package stea1th.chess.figures;

import stea1th.chess.helpers.ReflectionsHelper;

import java.lang.reflect.InvocationTargetException;
import java.util.Set;

public class FigureFactory {

    private FigureFactory() {
    }

    static {
        ReflectionsHelper.register(AbstractFigure.class);
    }

    public static Figure createFigure(String key, Integer position, boolean isWhite) {
        try {
            return AbstractFigure.newInstance(key, position, isWhite);
        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException | NoSuchMethodException | InvocationTargetException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Set<String> getFigureNames() {
        return AbstractFigure.getFigureNames();
    }
}
