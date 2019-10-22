package stea1th.chess.rules.figures;

import stea1th.chess.figures.Figure;
import stea1th.chess.helpers.ReflectionsHelper;

public class FigureRuleFactory {

    private FigureRuleFactory() {
    }

    static {
        ReflectionsHelper.register(AbstractRule.class);
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
