package stea1th.chess.figures;

import org.reflections.Reflections;

import java.lang.reflect.InvocationTargetException;
import java.util.Set;

public class FigureFactory {

    private FigureFactory() {
    }

    static {
        Reflections reflections = new Reflections(AbstractFigure.class);
        Set<Class<? extends AbstractFigure>> subTypes = reflections.getSubTypesOf(AbstractFigure.class);
        subTypes.forEach(i-> System.out.println(i.getName()));
        subTypes.forEach(i -> {
            try {
                i.getMethod("register").invoke(i.newInstance());
            } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException | InstantiationException e) {
                e.printStackTrace();
            }
        });
    }

    public static void test(){

    }
}
