package stea1th.chess.rules.figures;

import org.reflections.Reflections;
import stea1th.chess.figures.Figure;

import java.lang.reflect.InvocationTargetException;
import java.util.Set;

public class FigureRuleFactory {

    private FigureRuleFactory() {
    }

    static {
        Reflections reflections = new Reflections(AbstractRule.class);
        Set<Class<? extends AbstractRule>> subTypes = reflections.getSubTypesOf(AbstractRule.class);
        subTypes.forEach(i -> {
            try {
                i.getMethod("register").invoke(i.newInstance());
            } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException | InstantiationException e) {
                e.printStackTrace();
            }
        });
    }

    public static Rule createRule(Figure figure) {
        try {
            Rule rule = AbstractRule.newInstance(figure);
            rule.setMainFigure(figure);
            return rule;
        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException e) {
            e.printStackTrace();
        }
        return null;
    }
}
