package stea1th.chess.moves;

import org.reflections.Reflections;

import java.util.Set;

public class MoveFactory {

    private MoveFactory() {}

    static {
        Reflections reflections = new Reflections(Move.class);
        Set<Class<? extends Move>> subTypes = reflections.getSubTypesOf(Move.class);
        subTypes.forEach(i-> System.out.println(i.getSimpleName()));
    }

    public static void test() {

    }


}
