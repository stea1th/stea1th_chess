package stea1th.chess.helpers;

import org.reflections.Reflections;

import java.lang.reflect.InvocationTargetException;
import java.util.Set;

public class ReflectionsHelper {

    private ReflectionsHelper() {
    }

    public static <T> void register(Class<T> clazz) {
        Reflections reflections = new Reflections(clazz);
        Set<Class<? extends T>> subTypes = reflections.getSubTypesOf(clazz);
        subTypes.forEach(i -> {
            try {
                i.getMethod("register").invoke(i.newInstance());
            } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException | InstantiationException e) {
                e.printStackTrace();
            }
        });
    }
}
