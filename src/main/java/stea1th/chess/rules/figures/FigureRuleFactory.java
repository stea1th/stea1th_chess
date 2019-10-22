package stea1th.chess.rules.figures;

import org.reflections.Reflections;
import stea1th.chess.pieces.Piece;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLOutput;
import java.util.Arrays;
import java.util.Set;

public class FigureRuleFactory {

    private FigureRuleFactory() {
    }

    static {
        Reflections reflections = new Reflections(AbstractFigureRule.class);
        Set<Class<? extends AbstractFigureRule>> subTypes = reflections.getSubTypesOf(AbstractFigureRule.class);
        subTypes.forEach(i -> {
            try {
                i.getMethod("register").invoke(i.newInstance());
            } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException | InstantiationException e) {
                e.printStackTrace();
            }
        });
    }

    public static FigureRule createRule(Piece piece) {
        try {
            FigureRule rule = AbstractFigureRule.newInstance(piece);
            rule.setMainPiece(piece);
            return rule;
        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException e) {
            e.printStackTrace();
        }
        return null;
    }
}
